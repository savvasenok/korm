package xyz.savvamirzoyan.android.korm.ui.preview.models

import xyz.savvamirzoyan.android.korm.ui.preview.Constants
import xyz.savvamirzoyan.android.korm.ui.text.KormTextInputModel

internal val kormTextInputModels = listOf(
    // Empty
    KormTextInputModel(),

    // Only main word and sentence
    KormTextInputModel(Constants.WORD),
    KormTextInputModel(Constants.SENTENCE),

    // Disabled empty
    KormTextInputModel().copy(enabled = false),

    // Disabled non-empty
    KormTextInputModel(Constants.WORD).copy(enabled = false),

    // Error empty
    KormTextInputModel().copy(error = Constants.SENTENCE),

    // Error non-empty
    KormTextInputModel(Constants.WORD).copy(error = Constants.SENTENCE),

    // Error non-empty and disabled
    KormTextInputModel(Constants.WORD).copy(enabled = false, error = Constants.SENTENCE),

    // Long text
    KormTextInputModel(Constants.TEXT),
)