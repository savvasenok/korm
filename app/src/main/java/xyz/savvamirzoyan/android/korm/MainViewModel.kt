package xyz.savvamirzoyan.android.korm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.android.korm.contract.KormFieldInitializer
import xyz.savvamirzoyan.android.korm.contract.KormFieldUpdater
import xyz.savvamirzoyan.android.korm.model.KormFieldModel
import kotlin.time.Duration.Companion.seconds

private val GENDERS = listOf("Male", "Female", "Other")

class MainViewModel(
    private val kormFieldUpdater: KormFieldUpdater,
    private val kormFieldInitializer: KormFieldInitializer,
) : ViewModel(), KormFieldUpdater by kormFieldUpdater {

    init {
        viewModelScope.launch {
            // Simulate server delay
            delay(3.seconds)

            kormFieldInitializer.clear()
            initKormManager()
        }
    }

    val state: StateFlow<List<List<KormFieldModel>>> = kormFieldUpdater.uiFlow
        .map(::categorise)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private fun categorise(list: List<KormFieldModel>): List<List<KormFieldModel>> {
        var currentCategory = ""
        val categoryStack = mutableListOf<KormFieldModel>()
        val categories = mutableListOf<List<KormFieldModel>>()

        for (model in list) {

            val category = model.fieldId.split("/").first()
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

    private fun initKormManager() {
        kormFieldInitializer.addTextField("user/name", "")
        kormFieldInitializer.addNumberField("user/age", null)
        kormFieldInitializer.addNumberField("user/height", null, enabled = false)
        kormFieldInitializer.addTextField("user/mail", "john.doe@mail.com", enabled = false)
        kormFieldInitializer.addDropdownField("user/gender", 1, options = GENDERS)

        kormFieldInitializer.addTextField("vehicle/make", "Lancia")
        kormFieldInitializer.addCheckBoxField(
            id = "vehicle/electric",
            description = "Car is electric",
            isChecked = true
        )
        kormFieldInitializer.addDropdownField(
            id = "vehicle/price-range",
            index = 3,
            options = listOf("< 20k", "20k < 100k", "100k < 200k", "200k+")
        )
    }
}