package xyz.savvamirzoyan.android.korm.ui.preview.models

import xyz.savvamirzoyan.android.korm.model.KormFieldId
import xyz.savvamirzoyan.android.korm.model.KormNumberInputModel
import xyz.savvamirzoyan.android.korm.ui.preview.Constants

internal val kormNumberInputModels = listOf(
    // Empty
    KormNumberInputModel(KormFieldId.empty()),

    // Only main word and sentence
    KormNumberInputModel(KormFieldId.empty(), Constants.WORD),
    KormNumberInputModel(KormFieldId.empty(), Constants.SENTENCE),

    // Disabled empty
    KormNumberInputModel(KormFieldId.empty()).copy(enabled = false),

    // Disabled non-empty
    KormNumberInputModel(KormFieldId.empty(), Constants.WORD).copy(enabled = false),

    // Error empty
    KormNumberInputModel(KormFieldId.empty()).copy(error = Constants.SENTENCE),

    // Error non-empty
    KormNumberInputModel(KormFieldId.empty(), Constants.WORD).copy(error = Constants.SENTENCE),

    // Error non-empty and disabled
    KormNumberInputModel(KormFieldId.empty(), Constants.WORD).copy(
        enabled = false,
        error = Constants.SENTENCE
    ),

    // Long text
    KormNumberInputModel(KormFieldId.empty(), Constants.TEXT),
)