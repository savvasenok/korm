package xyz.savvamirzoyan.android.korm.model

import androidx.compose.ui.graphics.vector.ImageVector

data class KormTextInputModel internal constructor(
    private val id: KormFieldId,
    val value: String,
    val enabled: Boolean = true,
    val error: String? = null,
    val label: String? = null,
    val placeholder: String? = null,
    val icon: ImageVector? = null
) : KormFieldModel(id) {
    internal constructor(id: KormFieldId) : this(id, value = "")
}