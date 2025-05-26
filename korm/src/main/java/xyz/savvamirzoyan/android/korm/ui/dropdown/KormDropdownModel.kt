package xyz.savvamirzoyan.android.korm.ui.dropdown

import xyz.savvamirzoyan.android.korm.ui.text.KormTextInputModel

data class KormDropdownModel(
    val value: KormTextInputModel,
    val options: List<String>,
)