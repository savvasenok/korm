package xyz.savvamirzoyan.android.korm.ui.preview

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

internal object Constants {

    const val WORD = "Korm"
    const val SENTENCE = "Lorem ipsum dolor sie amet"
    val TEXT = LoremIpsum(42).values.joinToString(separator = " ")
}