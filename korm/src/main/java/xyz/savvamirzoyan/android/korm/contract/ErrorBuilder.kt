package xyz.savvamirzoyan.android.korm.contract

import xyz.savvamirzoyan.android.korm.model.KormFieldId

interface ErrorBuilder {

    fun getErrorString(id: KormFieldId, value: String): String?
    fun getErrorString(id: KormFieldId, value: Double?): String?
    fun getErrorString(id: KormFieldId, value: Int): String?
}