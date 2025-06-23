package xyz.savvamirzoyan.android.korm.ui.dropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import xyz.savvamirzoyan.android.korm.model.KormDropdownModel
import xyz.savvamirzoyan.android.korm.model.KormFieldId
import xyz.savvamirzoyan.android.korm.ui.preview.KormPreviewTheme
import xyz.savvamirzoyan.android.korm.ui.preview.models.kormDropdownModels
import xyz.savvamirzoyan.android.korm.ui.text.KormTextInput
import xyz.savvamirzoyan.android.korm.ui.text.LocalKormAllowClearTrailingIcon
import xyz.savvamirzoyan.android.korm.ui.text.LocalKormIsReadOnly

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KormDropdownPicker(
    modifier: Modifier = Modifier,
    model: KormDropdownModel,
    onSelect: (id: KormFieldId, index: Int) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = it && model.value.enabled
        },
    ) {
        CompositionLocalProvider(
            LocalKormIsReadOnly provides true,
            LocalKormAllowClearTrailingIcon provides false
        ) {
            KormTextInput(
                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                model = model.value,
                onChange = { _, _ -> /* is not invoked, bc is "read-only" */ },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                model.options.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = selectionOption,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        },
                        onClick = {
                            onSelect(model.fieldId, index)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun KormOutlinedDropdownPickerPreview() {
    KormPreviewTheme {
        for (model in kormDropdownModels) {
            KormDropdownPicker(
                model = model,
                onSelect = { _, _ -> }
            )
        }
    }
}