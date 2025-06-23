package xyz.savvamirzoyan.android.korm.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue

data class KormNumberInputModel internal constructor(
    private val id: KormFieldId,
    val value: TextFieldValue,
    val enabled: Boolean = true,
    val error: String? = null,
    val label: String? = null,
    val placeholder: String? = null,
    val icon: ImageVector? = null
) : KormFieldModel(id) {
    internal constructor(id: KormFieldId) : this(id, TextFieldValue(text = ""))
    internal constructor(id: KormFieldId, value: String) : this(id, TextFieldValue(text = value))
}