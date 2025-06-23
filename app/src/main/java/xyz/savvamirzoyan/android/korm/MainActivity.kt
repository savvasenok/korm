package xyz.savvamirzoyan.android.korm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import xyz.savvamirzoyan.android.korm.ui.KormFieldUi
import xyz.savvamirzoyan.android.korm.ui.compositions.KormInputStyle
import xyz.savvamirzoyan.android.korm.ui.compositions.LocalKormInputPreferredStyle
import xyz.savvamirzoyan.android.korm.ui.theme.KormTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KormTheme {

                val state = viewModel.state.collectAsStateWithLifecycle().value

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(8.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (state.isEmpty()) {
                            ContainedLoadingIndicator(
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {

                            var counter by remember { mutableIntStateOf(0) }
                            val styles = KormInputStyle.entries

                            for (list in state) {

                                CompositionLocalProvider(LocalKormInputPreferredStyle provides styles[counter % styles.size]) {
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(16.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            for (item in list) {
                                                KormFieldUi(
                                                    model = item,
                                                    onCheckedChange = viewModel::update,
                                                    onSelect = viewModel::update,
                                                    onTextChange = viewModel::updateText,
                                                    onNumberChange = viewModel::updateNumber
                                                )
                                            }
                                        }
                                    }
                                }

                                counter++
                            }
                        }
                    }
                }
            }
        }
    }
}