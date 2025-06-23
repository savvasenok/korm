package xyz.savvamirzoyan.android.korm.contract

import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import xyz.savvamirzoyan.android.korm.model.KormCheckBoxModel
import xyz.savvamirzoyan.android.korm.model.KormDropdownModel
import xyz.savvamirzoyan.android.korm.model.KormFieldId
import xyz.savvamirzoyan.android.korm.model.KormFieldModel
import xyz.savvamirzoyan.android.korm.model.KormNumberInputModel
import xyz.savvamirzoyan.android.korm.model.KormTextInputModel
import xyz.savvamirzoyan.android.korm.utils.toNumericValue

class DefaultKormFieldUpdater(
    private val errorBuilder: ErrorBuilder
) : KormFieldUpdater {

    private val fieldIdToModel = mutableMapOf<KormFieldId, KormFieldModel>()
    private val fieldIdToIndex = mutableMapOf<KormFieldId, Int>()
    private val kormModels = mutableListOf<KormFieldModel>()

    private val _uiFlow = MutableStateFlow<List<KormFieldModel>>(emptyList())
    val uiFlow: StateFlow<List<KormFieldModel>> = _uiFlow.asStateFlow()

    fun setFields(models: List<KormFieldModel>) {
        kormModels.clear()
        kormModels.addAll(models)

        val pairs = models.map { model -> Pair(model.fieldId, model) }
        fieldIdToModel.clear()
        fieldIdToModel.putAll(pairs)

        val indexes = models.mapIndexed { index, model -> Pair(model.fieldId, index) }
        fieldIdToIndex.clear()
        fieldIdToIndex.putAll(indexes)

        updateFlow()
    }

    override fun update(fieldId: KormFieldId, value: Boolean) {
        val model = (fieldIdToModel[fieldId] as? KormCheckBoxModel)
            ?.copy(isChecked = value)

        if (model == null) {
            throw IllegalStateException("Expected KormCheckBoxModel by id `$fieldId`")
        }

        updateModel(model)
    }

    override fun update(fieldId: KormFieldId, index: Int) {
        val model = (fieldIdToModel[fieldId] as? KormDropdownModel)
            ?.let {
                val label = it.options.getOrNull(index)
                val error = errorBuilder.getErrorString(fieldId, index)
                val value = it.value.copy(value = label ?: "", error = error)

                it.copy(value = value)
            }

        if (model == null) {
            throw IllegalStateException("Expected KormDropdownModel by id `$fieldId`")
        }

        updateModel(model)
    }

    override fun updateText(fieldId: KormFieldId, text: String) {
        val model = (fieldIdToModel[fieldId] as? KormTextInputModel)
            ?.let {
                val error = errorBuilder.getErrorString(fieldId, text)
                it.copy(value = text, error = error)
            }

        if (model == null) {
            throw IllegalStateException("Expected KormTextInputModel by id `$fieldId`")
        }

        updateModel(model)
    }

    override fun updateNumber(fieldId: KormFieldId, number: String) {
        val model = (fieldIdToModel[fieldId] as? KormNumberInputModel)
            ?.let {
                val numericValue = number.toNumericValue()
                val error = errorBuilder.getErrorString(fieldId, numericValue)

                it.copy(value = TextFieldValue(number), error = error)
            }

        if (model == null) {
            throw IllegalStateException("Expected KormNumberInputModel by id `$fieldId`")
        }

        updateModel(model)
    }

    private fun updateModel(model: KormFieldModel) {
        fieldIdToModel[model.fieldId] = model
        val index = fieldIdToIndex[model.fieldId]!!
        kormModels[index] = model

        updateFlow()
    }

    private fun updateFlow() {
        _uiFlow.update { kormModels.toList() }
    }
}
