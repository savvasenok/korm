package xyz.savvamirzoyan.android.korm.ui

import androidx.compose.runtime.Composable
import xyz.savvamirzoyan.android.korm.model.KormCheckBoxModel
import xyz.savvamirzoyan.android.korm.model.KormDropdownModel
import xyz.savvamirzoyan.android.korm.model.KormFieldModel
import xyz.savvamirzoyan.android.korm.model.KormNumberInputModel
import xyz.savvamirzoyan.android.korm.model.KormTextInputModel
import xyz.savvamirzoyan.android.korm.ui.checkbox.KormCheckBox
import xyz.savvamirzoyan.android.korm.ui.dropdown.KormDropdownPicker
import xyz.savvamirzoyan.android.korm.ui.number.KormNumberInput
import xyz.savvamirzoyan.android.korm.ui.text.KormTextInput

@Composable
fun KormFieldUi(
    model: KormFieldModel,
    onCheckedChange: (fieldId: String, value: Boolean) -> Unit,
    onSelect: (fieldId: String, index: Int) -> Unit,
    onTextChange: (fieldId: String, text: String) -> Unit,
    onNumberChange: (fieldId: String, text: String) -> Unit
) {

    when (model) {
        is KormCheckBoxModel -> {
            KormCheckBox(
                model = model,
                onCheckedChange = onCheckedChange
            )
        }

        is KormDropdownModel -> {
            KormDropdownPicker(
                model = model,
                onSelect = onSelect
            )
        }

        is KormNumberInputModel -> {
            KormNumberInput(
                model = model,
                onChange = onNumberChange
            )
        }

        is KormTextInputModel -> {
            KormTextInput(
                model = model,
                onChange = onTextChange
            )
        }
    }
}





















