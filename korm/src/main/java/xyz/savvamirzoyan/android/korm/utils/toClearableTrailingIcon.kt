package xyz.savvamirzoyan.android.korm.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import xyz.savvamirzoyan.android.korm.ui.text.LocalKormAllowClearTrailingIcon

@Composable
internal fun (@Composable (() -> Unit))?.toClearableTrailingIcon(
    value: String,
    enabled: Boolean,
    onClearClick: () -> Unit
): @Composable (() -> Unit)? =
    if (value.isNotBlank() && enabled && LocalKormAllowClearTrailingIcon.current) {
        {
            IconButton(onClick = onClearClick) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }
    } else {
        this
    }