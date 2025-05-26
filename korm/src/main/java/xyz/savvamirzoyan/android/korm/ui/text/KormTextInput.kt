package xyz.savvamirzoyan.android.korm.ui.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import xyz.savvamirzoyan.android.korm.ui.preview.KormPreviewTheme
import xyz.savvamirzoyan.android.korm.ui.preview.models.kormTextInputModels

@Composable
fun KormTextInput(
    modifier: Modifier = Modifier,
    model: KormTextInputModel,
    onChange: (text: String) -> Unit
) {

    var lastError by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(model.error) {
        if (model.error != null) {
            lastError = model.error
        }
    }

    Column {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = model.value,
            enabled = model.enabled,
            isError = model.error != null,
            maxLines = 3,
            readOnly = LocalKormIsReadOnly.current,
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
private fun KormTextInputPreview() {
    KormPreviewTheme {
        for (model in kormTextInputModels) {
            KormTextInput(
                model = model,
                onChange = {}
            )
        }
    }
}

