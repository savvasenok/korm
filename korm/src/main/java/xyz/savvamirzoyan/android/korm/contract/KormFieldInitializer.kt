package xyz.savvamirzoyan.android.korm.contract

interface KormFieldInitializer {

    fun clear()

    fun addCheckBoxField(
        id: String,
        description: String,
        isChecked: Boolean = false,
        enabled: Boolean = true
    )

    fun addTextField(
        id: String,
        value: String,
        enabled: Boolean = true
    )

    fun addNumberField(
        id: String,
        value: Double?,
        enabled: Boolean = true
    )

    fun addDropdownField(
        id: String,
        index: Int?,
        options: List<String>,
        enabled: Boolean = true
    )
}