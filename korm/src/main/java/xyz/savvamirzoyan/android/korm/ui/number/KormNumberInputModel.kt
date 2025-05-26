package xyz.savvamirzoyan.android.korm.ui.number

import androidx.compose.ui.text.input.TextFieldValue

data class KormNumberInputModel(
    val value: TextFieldValue,
    val enabled: Boolean = true,
    val error: String? = null,
    val isInteger: Boolean = true
) {
    constructor() : this(TextFieldValue(text = ""))
    constructor(value: String) : this(TextFieldValue(text = value))
}