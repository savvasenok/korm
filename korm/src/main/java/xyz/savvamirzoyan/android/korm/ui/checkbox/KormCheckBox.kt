package xyz.savvamirzoyan.android.korm.ui.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import xyz.savvamirzoyan.android.korm.ui.preview.KormPreviewTheme
import xyz.savvamirzoyan.android.korm.ui.preview.models.kormCheckBoxModels

// TODO: provide a local-composition parameter, that defines whether checkbox
//       is on the right or the left side

// TODO: Provide a local-composition parameter, that defines roundness of the clickable area
//       Currently 12.dp

@Composable
fun KormCheckBox(
    modifier: Modifier = Modifier,
    model: KormCheckBoxModel,
    onCheckedChange: (value: Boolean) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                enabled = model.enabled,
                role = Role.Checkbox,
                onClick = { onCheckedChange(!model.isChecked) }
            )
            .heightIn(LocalMinimumInteractiveComponentSize.current)
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            text = model.description,
            color = MaterialTheme.colorScheme.onBackground
                .copy(alpha = if (model.enabled) ContentAlpha.high else ContentAlpha.disabled)
        )

        Checkbox(
            checked = model.isChecked,
            onCheckedChange = onCheckedChange,
            enabled = model.enabled,
        )
    }
}


@PreviewLightDark
@Composable
fun KormCheckBoxPreview() {
    KormPreviewTheme {
        for (model in kormCheckBoxModels) {
            KormCheckBox(
                model = model,
                onCheckedChange = {}
            )
        }
    }
}