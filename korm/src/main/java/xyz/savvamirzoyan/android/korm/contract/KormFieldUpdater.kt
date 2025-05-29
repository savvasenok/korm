package xyz.savvamirzoyan.android.korm.contract

import kotlinx.coroutines.flow.StateFlow
import xyz.savvamirzoyan.android.korm.model.KormFieldModel

interface KormFieldUpdater {

    val uiFlow: StateFlow<List<KormFieldModel>>

    fun update(fieldId: String, value: Boolean)
    fun update(fieldId: String, index: Int)
    fun updateText(fieldId: String, text: String)
    fun updateNumber(fieldId: String, number: String)
}