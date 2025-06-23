package xyz.savvamirzoyan.android.korm.model

data class KormCheckBoxModel internal constructor(
    private val id: KormFieldId,
    val isChecked: Boolean,
    val description: String,
    val enabled: Boolean = true,
) : KormFieldModel(id)