package xyz.savvamirzoyan.android.korm.ui.preview.models

import xyz.savvamirzoyan.android.korm.model.KormNumberInputModel
import xyz.savvamirzoyan.android.korm.ui.preview.Constants

internal val kormNumberInputModels = listOf(
    // Empty
    KormNumberInputModel(""),

    // Only main word and sentence
    KormNumberInputModel("", Constants.WORD),
    KormNumberInputModel("", Constants.SENTENCE),

    // Disabled empty
    KormNumberInputModel("").copy(enabled = false),

    // Disabled non-empty
    KormNumberInputModel("", Constants.WORD).copy(enabled = false),

    // Error empty
    KormNumberInputModel("").copy(error = Constants.SENTENCE),

    // Error non-empty
    KormNumberInputModel("", Constants.WORD).copy(error = Constants.SENTENCE),

    // Error non-empty and disabled
    KormNumberInputModel("", Constants.WORD).copy(enabled = false, error = Constants.SENTENCE),

    // Long text
    KormNumberInputModel("", Constants.TEXT),
)