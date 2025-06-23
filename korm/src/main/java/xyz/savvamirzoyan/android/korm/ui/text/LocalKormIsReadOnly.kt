package xyz.savvamirzoyan.android.korm.ui.text

import androidx.compose.runtime.compositionLocalOf

/**
 * Composition local key for allowing or forbidding child composables to be edited
 *
 * Example use: KormDropdownPicker uses KormTextInput, which is not allowed to be edited
 * manually by user
 **/
internal val LocalKormIsReadOnly = compositionLocalOf { false }