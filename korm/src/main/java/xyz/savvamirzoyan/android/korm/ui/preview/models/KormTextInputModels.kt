package xyz.savvamirzoyan.android.korm.ui.preview.models

import androidx.compose.ui.text.input.TextFieldValue
import xyz.savvamirzoyan.android.korm.model.KormTextInputModel
import xyz.savvamirzoyan.android.korm.ui.preview.Constants

internal val kormTextInputModels = listOf(
    // Empty
    KormTextInputModel(""),

    // With label non empty
    KormTextInputModel("", value = TextFieldValue(Constants.WORD), label = "Label"),

    // With label and placeholder. Empty
    KormTextInputModel(
        "",
        value = TextFieldValue(""),
        label = "Label",
        placeholder = "Placeholder"
    ),

    // Disabled empty
    KormTextInputModel("").copy(enabled = false),

    // Disabled non-empty
    KormTextInputModel("", Constants.WORD).copy(enabled = false),

    // Error empty
    KormTextInputModel("").copy(error = Constants.SENTENCE),

    // Error non-empty
    KormTextInputModel("", Constants.WORD).copy(error = Constants.SENTENCE),

    // Error non-empty and disabled
    KormTextInputModel("", Constants.WORD).copy(enabled = false, error = Constants.SENTENCE),

    // Long text
    KormTextInputModel("", Constants.TEXT),
)