package xyz.savvamirzoyan.android.korm.ui.text

import androidx.compose.runtime.compositionLocalOf

/**
 * Composition local key for allowing or forbidding child composables to display a "Clear"
 * button as the trailing one
 *
 * Example use: KormDropdownPicker uses KormTextInput, which places icon on non-empty input.
 * This behaviour conflicts with expected Dropdown icon
 **/
internal val LocalKormAllowClearTrailingIcon = compositionLocalOf { true }