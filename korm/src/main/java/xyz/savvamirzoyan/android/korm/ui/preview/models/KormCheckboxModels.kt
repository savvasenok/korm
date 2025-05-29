package xyz.savvamirzoyan.android.korm.ui.preview.models

import xyz.savvamirzoyan.android.korm.model.KormCheckBoxModel
import xyz.savvamirzoyan.android.korm.ui.preview.Constants

internal val kormCheckBoxModels = listOf(

    // Word and text
    KormCheckBoxModel("", true, Constants.WORD),
    KormCheckBoxModel("", false, Constants.TEXT),

    // Disabled
    KormCheckBoxModel("", true, Constants.WORD, enabled = false),
    KormCheckBoxModel("", false, Constants.WORD, enabled = false),
)