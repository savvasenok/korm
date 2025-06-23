package xyz.savvamirzoyan.android.korm.contract

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.text.input.TextFieldValue
import xyz.savvamirzoyan.android.korm.model.KormCheckBoxModel
import xyz.savvamirzoyan.android.korm.model.KormDropdownModel
import xyz.savvamirzoyan.android.korm.model.KormFieldId
import xyz.savvamirzoyan.android.korm.model.KormFieldModel
import xyz.savvamirzoyan.android.korm.model.KormNumberInputModel
import xyz.savvamirzoyan.android.korm.model.KormTextInputModel

class KormFieldBuilder {

    private val models = mutableListOf<KormFieldModel>()

    fun clear() {
        models.clear()
    }

    fun getFields(): List<KormFieldModel> = models.toList()

    fun addCheckBoxField(
        id: String,
        description: String,
        isChecked: Boolean = false,
        enabled: Boolean = true
    ) {
        require(id.isNotBlank())
        require(description.isNotBlank())

        val model = KormCheckBoxModel(
            id = KormFieldId(id),
            isChecked = isChecked,
            description = description,
            enabled = enabled
        )

        models.add(model)
    }

    fun addTextField(
        id: String,
        value: String,
        enabled: Boolean = true,
        label: String? = null,
        placeholder: String? = null
    ) {
        require(id.isNotBlank())

        val model = KormTextInputModel(
            id = KormFieldId(id),
            value = value,
            enabled = enabled,
            label = label,
            placeholder = placeholder
        )

        models.add(model)
    }

    fun addNumberField(
        id: String,
        value: Double?,
        enabled: Boolean = true,
        label: String? = null,
        placeholder: String? = null
    ) {
        require(id.isNotBlank())

        // TODO: implement proper mapping to string

        val model = KormNumberInputModel(
            id = KormFieldId(id),
            value = TextFieldValue(value?.toString() ?: ""),
            enabled = enabled,
            label = label,
            placeholder = placeholder
        )

        models.add(model)
    }

    fun addDropdownField(
        id: String,
        index: Int?,
        options: List<String>,
        enabled: Boolean = true,
        label: String? = null,
        placeholder: String? = null
    ) {
        require(id.isNotBlank())
        require((index ?: 0) >= 0)
        if (index != null) {
            require(index in options.indices)
        }
        require(options.isNotEmpty())

        val selected = options[index ?: 0]

        val textModel = KormTextInputModel(
            id = KormFieldId(id),
            value = selected,
            enabled = enabled,
            icon = Icons.Default.ArrowDropDown,
            label = label,
            placeholder = placeholder
        )
        val model = KormDropdownModel(
            value = textModel,
            options = options
        )

        models.add(model)
    }
}