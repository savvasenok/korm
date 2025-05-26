package xyz.savvamirzoyan.android.korm.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun isPreview(): Boolean = LocalInspectionMode.current
