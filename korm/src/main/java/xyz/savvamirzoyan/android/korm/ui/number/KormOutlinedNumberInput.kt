package xyz.savvamirzoyan.android.korm.ui.number

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import xyz.savvamirzoyan.android.korm.ui.preview.KormPreviewTheme
import xyz.savvamirzoyan.android.korm.ui.preview.models.kormNumberInputModels

@Composable
fun KormOutlinedNumberInput(
    modifier: Modifier = Modifier,
    model: KormNumberInputModel,
    onChange: (text: String) -> Unit
) {

    var lastError by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(model.error) {
        if (model.error != null) {
            lastError = model.error
        }
    }

    Column {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = model.value,
            enabled = model.enabled,
            isError = model.error != null,
            maxLines = 3,
            label = {},
            placeholder = {},
            onValueChange = { onChange(it.text) },
        )

        AnimatedVisibility((model.error) != null) {
            Text(
                (model.error ?: lastError) ?: "",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun KormOutlinedNumberInputPreview() {
    KormPreviewTheme {
        for (model in kormNumberInputModels) {
            KormOutlinedNumberInput(
                model = model,
                onChange = {}
            )
        }
    }
}

