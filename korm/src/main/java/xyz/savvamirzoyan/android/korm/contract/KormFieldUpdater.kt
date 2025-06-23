package xyz.savvamirzoyan.android.korm.contract

import xyz.savvamirzoyan.android.korm.model.KormFieldId

interface KormFieldUpdater {

    fun update(fieldId: KormFieldId, value: Boolean)
    fun update(fieldId: KormFieldId, index: Int)
    fun updateText(fieldId: KormFieldId, text: String)
    fun updateNumber(fieldId: KormFieldId, number: String)
}