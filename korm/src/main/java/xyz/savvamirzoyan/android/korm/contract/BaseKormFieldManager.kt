package xyz.savvamirzoyan.android.korm.contract

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import xyz.savvamirzoyan.android.korm.model.KormCheckBoxModel
import xyz.savvamirzoyan.android.korm.model.KormDropdownModel
import xyz.savvamirzoyan.android.korm.model.KormFieldModel
import xyz.savvamirzoyan.android.korm.model.KormNumberInputModel
import xyz.savvamirzoyan.android.korm.model.KormTextInputModel
import xyz.savvamirzoyan.android.korm.ui.preview.Constants

class BaseKormFieldManager : KormFieldUpdater, KormFieldInitializer {

    private val fieldIdToModel = mutableMapOf<String, KormFieldModel>()
    private val fieldIdToIndex = mutableMapOf<String, Int>()
    private val models = mutableListOf<KormFieldModel>()

    private val _uiFlow = MutableStateFlow<List<KormFieldModel>>(emptyList())
    override val uiFlow: StateFlow<List<KormFieldModel>> = _uiFlow.asStateFlow()

    override fun update(fieldId: String, value: Boolean) {
        val model = (fieldIdToModel[fieldId] as? KormCheckBoxModel)
            ?.copy(isChecked = value)

        if (model == null) {
            throw IllegalStateException("Expected KormCheckBoxModel by id `$fieldId`")
        } else {
            fieldIdToModel[fieldId] = model
            val index = fieldIdToIndex[fieldId]!!
            models[index] = model

            updateFlow()
        }
    }

    override fun update(fieldId: String, index: Int) {
        val model = (fieldIdToModel[fieldId] as? KormDropdownModel)
            ?.let {
                val label = it.options[index]
                val value = it.value.copy(value = TextFieldValue(label))
                it.copy(value = value)
            }

        if (model == null) {
            throw IllegalStateException("Expected KormDropdownModel by id `$fieldId`")
        } else {
            fieldIdToModel[fieldId] = model
            val modelIndex = fieldIdToIndex[fieldId]!!
            models[modelIndex] = model

            updateFlow()
        }
    }

    override fun updateText(fieldId: String, text: String) {
        val model = (fieldIdToModel[fieldId] as? KormTextInputModel)
            ?.copy(value = TextFieldValue(text))

        if (model == null) {
            throw IllegalStateException("Expected KormTextInputModel by id `$fieldId`")
        } else {
            fieldIdToModel[fieldId] = model
            val index = fieldIdToIndex[fieldId]!!
            models[index] = model

            updateFlow()
        }
    }

    override fun updateNumber(fieldId: String, number: String) {
        val model = (fieldIdToModel[fieldId] as? KormNumberInputModel)
            ?.copy(value = TextFieldValue(number))

        if (model == null) {
            throw IllegalStateException("Expected KormNumberInputModel by id `$fieldId`")
        } else {
            fieldIdToModel[fieldId] = model
            val index = fieldIdToIndex[fieldId]!!
            models[index] = model

            updateFlow()
        }
    }

    override fun clear() {
        fieldIdToModel.clear()
        fieldIdToIndex.clear()
        models.clear()
    }

    override fun addCheckBoxField(
        id: String,
        description: String,
        isChecked: Boolean,
        enabled: Boolean
    ) {
        require(id.isNotBlank())
        require(description.isNotBlank())

        val model = KormCheckBoxModel(
            id = id,
            isChecked = isChecked,
            description = description,
            enabled = enabled
        )

        fieldIdToModel[id] = model
        fieldIdToIndex[id] = models.size
        models.add(model)

        updateFlow()
    }

    override fun addTextField(id: String, value: String, enabled: Boolean) {
        require(id.isNotBlank())

        val model = KormTextInputModel(
            id = id,
            value = TextFieldValue(value),
            enabled = enabled,
            label = Constants.WORD
        )

        fieldIdToModel[id] = model
        fieldIdToIndex[id] = models.size
        models.add(model)

        updateFlow()
    }

    override fun addNumberField(id: String, value: Double?, enabled: Boolean) {
        require(id.isNotBlank())

        // TODO: implement proper mapping to string

        val model = KormNumberInputModel(
            id = id,
            value = TextFieldValue(value?.toString() ?: ""),
            enabled = enabled,
        )

        fieldIdToModel[id] = model
        fieldIdToIndex[id] = models.size
        models.add(model)

        updateFlow()
    }

    override fun addDropdownField(
        id: String,
        index: Int?,
        options: List<String>,
        enabled: Boolean
    ) {
        require(id.isNotBlank())
        require((index ?: 0) >= 0)
        require(index in options.indices)
        require(options.isNotEmpty())

        val label = options[index ?: 0]

        val textModel = KormTextInputModel(
            id = id,
            value = TextFieldValue(label),
            enabled = enabled,
            icon = Icons.Default.ArrowDropDown,
        )
        val model = KormDropdownModel(
            value = textModel,
            options = options
        )

        fieldIdToModel[id] = model
        fieldIdToIndex[id] = models.size
        models.add(model)

        updateFlow()
    }

    private fun updateFlow() = _uiFlow.update { models.toList() }
}