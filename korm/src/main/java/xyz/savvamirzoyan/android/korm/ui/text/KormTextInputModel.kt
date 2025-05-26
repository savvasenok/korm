package xyz.savvamirzoyan.android.korm.ui.text

import androidx.compose.ui.text.input.TextFieldValue

data class KormTextInputModel(
    val value: TextFieldValue,
    val enabled: Boolean = true,
    val error: String? = null,
) {
    constructor() : this(TextFieldValue(text = ""))
    constructor(value: String) : this(TextFieldValue(text = value))
}