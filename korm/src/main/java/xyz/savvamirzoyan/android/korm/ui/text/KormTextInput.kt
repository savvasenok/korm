package xyz.savvamirzoyan.android.korm.ui.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import xyz.savvamirzoyan.android.korm.model.KormTextInputModel
import xyz.savvamirzoyan.android.korm.ui.compositions.KormInputStyle
import xyz.savvamirzoyan.android.korm.ui.compositions.LocalKormInputPreferredStyle
import xyz.savvamirzoyan.android.korm.ui.preview.KormPreviewTheme
import xyz.savvamirzoyan.android.korm.ui.preview.models.kormTextInputModels


@Composable
internal fun KormTextInput(
    modifier: Modifier = Modifier,
    model: KormTextInputModel,
    onChange: (fieldId: String, text: String) -> Unit
) {
    val trailingIcon: (@Composable () -> Unit)? = model.icon
        ?.let { icon -> { Icon(icon, contentDescription = null) } }

    KormTextInput(
        modifier = modifier,
        model = model,
        onChange = onChange,
        trailingIcon = trailingIcon
    )
}

@Composable
internal fun KormTextInput(
    modifier: Modifier = Modifier,
    model: KormTextInputModel,
    trailingIcon: (@Composable () -> Unit)?,
    onChange: (fieldId: String, text: String) -> Unit
) {

    var lastError by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(model.error) {
        if (model.error != null) {
            lastError = model.error
        }
    }

    val label: (@Composable () -> Unit)? = model.label
        ?.let { label -> { Text(label) } }

    val placeholder: (@Composable () -> Unit)? = model.placeholder
        ?.let { placeholder -> { Text(placeholder) } }

    Column {

        val style = LocalKormInputPreferredStyle.current

        if (style == KormInputStyle.OUTLINE) {
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = model.value.text,
                enabled = model.enabled,
                isError = model.error != null,
                maxLines = 3,
                readOnly = LocalKormIsReadOnly.current,
                trailingIcon = trailingIcon,
                label = label,
                placeholder = placeholder,
                onValueChange = { onChange(model.fieldId, it) },
            )
        } else if (style == KormInputStyle.FILLED) {
            TextField(
                modifier = modifier.fillMaxWidth(),
                value = model.value.text,
                enabled = model.enabled,
                isError = model.error != null,
                maxLines = 3,
                readOnly = LocalKormIsReadOnly.current,
                trailingIcon = trailingIcon,
                label = label,
                placeholder = placeholder,
                onValueChange = { onChange(model.fieldId, it) },
            )
        }

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
private fun KormOutlinedTextInputPreview() {
    KormPreviewTheme {
        for (model in kormTextInputModels) {
            KormTextInput(
                model = model,
                onChange = { _, _ -> }
            )
        }
    }
}

