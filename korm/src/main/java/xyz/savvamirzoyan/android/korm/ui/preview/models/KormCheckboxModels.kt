package xyz.savvamirzoyan.android.korm.ui.preview.models

import xyz.savvamirzoyan.android.korm.model.KormCheckBoxModel
import xyz.savvamirzoyan.android.korm.model.KormFieldId
import xyz.savvamirzoyan.android.korm.ui.preview.Constants

internal val kormCheckBoxModels = listOf(

    // Word and text
    KormCheckBoxModel(KormFieldId.empty(), true, Constants.WORD),
    KormCheckBoxModel(KormFieldId.empty(), false, Constants.TEXT),

    // Disabled
    KormCheckBoxModel(KormFieldId.empty(), true, Constants.WORD, enabled = false),
    KormCheckBoxModel(KormFieldId.empty(), false, Constants.WORD, enabled = false),
)