package xyz.savvamirzoyan.android.korm.ui.preview.models

import xyz.savvamirzoyan.android.korm.model.KormDropdownModel
import xyz.savvamirzoyan.android.korm.ui.preview.Constants

private val options = listOf(Constants.WORD, Constants.SENTENCE, Constants.TEXT)

internal val kormDropdownModels = kormTextInputModels
    .map { model -> KormDropdownModel(value = model, options = options) }