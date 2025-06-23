package xyz.savvamirzoyan.android.korm.model

sealed class KormFieldModel(val fieldId: KormFieldId)

@JvmInline
value class KormFieldId internal constructor(val value: String) {

    companion object {
        internal fun empty() = KormFieldId("")
    }
}