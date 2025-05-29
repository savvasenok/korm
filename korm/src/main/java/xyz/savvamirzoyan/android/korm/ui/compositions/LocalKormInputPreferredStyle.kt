package xyz.savvamirzoyan.android.korm.ui.compositions

import androidx.compose.runtime.compositionLocalOf

val LocalKormInputPreferredStyle = compositionLocalOf { KormInputStyle.OUTLINE }

enum class KormInputStyle {
    OUTLINE, FILLED
    // TODO: Implement custom style by developer
}