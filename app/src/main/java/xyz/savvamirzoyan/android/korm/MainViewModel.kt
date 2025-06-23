package xyz.savvamirzoyan.android.korm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.android.korm.contract.DefaultKormFieldUpdater
import xyz.savvamirzoyan.android.korm.contract.KormFieldBuilder
import xyz.savvamirzoyan.android.korm.contract.KormFieldUpdater
import xyz.savvamirzoyan.android.korm.model.KormFieldId
import xyz.savvamirzoyan.android.korm.model.KormFieldModel

private val GENDERS = listOf("Male", "Female", "Other")

class MainViewModel : ViewModel(), KormFieldUpdater {

    private val kormFieldBuilder = KormFieldBuilder()
    private val kormFieldUpdater = DefaultKormFieldUpdater(DemoErrorBuilder())

    init {
        viewModelScope.launch {
            initKorm()
        }
    }

    val state: StateFlow<List<List<KormFieldModel>>> = kormFieldUpdater.uiFlow
        .map(::categorise)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    override fun update(fieldId: KormFieldId, value: Boolean) =
        kormFieldUpdater.update(fieldId, value)

    override fun update(fieldId: KormFieldId, index: Int) =
        kormFieldUpdater.update(fieldId, index)

    override fun updateText(fieldId: KormFieldId, text: String) =
        kormFieldUpdater.updateText(fieldId, text)

    override fun updateNumber(fieldId: KormFieldId, number: String) =
        kormFieldUpdater.updateNumber(fieldId, number)

    private fun categorise(list: List<KormFieldModel>): List<List<KormFieldModel>> {
        var currentCategory = ""
        val categoryStack = mutableListOf<KormFieldModel>()
        val categories = mutableListOf<List<KormFieldModel>>()

        for (model in list) {

            val category = model.fieldId.value.split("/").first()
            if (currentCategory == "") {
                currentCategory = category
            }

            if (category == currentCategory) {
                categoryStack.add(model)
            } else {
                categories.add(categoryStack.toList())
                categoryStack.clear()

                currentCategory = category
                categoryStack.add(model)
            }
        }

        if (categoryStack.isNotEmpty()) {
            categories.add(categoryStack.toList())
        }

        return categories.toList()
    }

    private fun initKorm() {
        kormFieldBuilder.clear()
        kormFieldBuilder.addTextField("user/name", "", label = "Name")
        kormFieldBuilder.addNumberField("user/age", null, label = "Age")
        kormFieldBuilder.addNumberField("user/height", null, label = "Height in meters")
        kormFieldBuilder.addTextField("user/mail", "john.doe@mail.com", label = "Email")
        kormFieldBuilder.addDropdownField("user/gender", null, options = GENDERS, label = "Gender")

        kormFieldBuilder.addTextField("vehicle/make", "Lancia", label = "Vehicle make")
        kormFieldBuilder.addCheckBoxField(
            id = "vehicle/electric",
            description = "Car is electric",
            isChecked = true,
        )
        kormFieldBuilder.addDropdownField(
            id = "vehicle/price-range",
            index = 3,
            options = listOf("< 20k", "20k < 100k", "100k < 200k", "200k+"),
            label = "Price range"
        )

        kormFieldUpdater.setFields(kormFieldBuilder.getFields())
    }
}