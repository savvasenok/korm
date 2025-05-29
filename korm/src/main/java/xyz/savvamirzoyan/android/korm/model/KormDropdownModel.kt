package xyz.savvamirzoyan.android.korm.model

data class KormDropdownModel internal constructor(
    val value: KormTextInputModel,
    val options: List<String>,
) : KormFieldModel(value.fieldId)