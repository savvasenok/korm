package xyz.savvamirzoyan.android.korm.ui.preview.models

import xyz.savvamirzoyan.android.korm.model.KormFieldId
import xyz.savvamirzoyan.android.korm.model.KormTextInputModel
import xyz.savvamirzoyan.android.korm.ui.preview.Constants

internal val kormTextInputModels = listOf(
    // Empty
    KormTextInputModel(KormFieldId.empty()),

    // With label non empty
    KormTextInputModel(KormFieldId.empty(), value = Constants.WORD, label = "Label"),

    // With label and placeholder. Empty
    KormTextInputModel(
        KormFieldId.empty(),
        value = "",
        label = "Label",
        placeholder = "Placeholder"
    ),

    // Disabled empty
    KormTextInputModel(KormFieldId.empty()).copy(enabled = false),

    // Disabled non-empty
    KormTextInputModel(KormFieldId.empty(), Constants.WORD).copy(enabled = false),

    // Error empty
    KormTextInputModel(KormFieldId.empty()).copy(error = Constants.SENTENCE),

    // Error non-empty
    KormTextInputModel(KormFieldId.empty(), Constants.WORD).copy(error = Constants.SENTENCE),

    // Error non-empty and disabled
    KormTextInputModel(KormFieldId.empty(), Constants.WORD).copy(
        enabled = false,
        error = Constants.SENTENCE
    ),

    // Long text
    KormTextInputModel(KormFieldId.empty(), Constants.TEXT),
)