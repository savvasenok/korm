package xyz.savvamirzoyan.android.korm.ui.preview.models

import xyz.savvamirzoyan.android.korm.ui.dropdown.KormDropdownModel
import xyz.savvamirzoyan.android.korm.ui.preview.Constants
import xyz.savvamirzoyan.android.korm.ui.text.KormTextInputModel

private val options = listOf(Constants.WORD, Constants.SENTENCE, Constants.TEXT)

internal val kormDropdownModels = kormTextInputModels
    .map { textModel -> KormDropdownModel(value = textModel, options = options) }