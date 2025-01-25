package my.app.moviesapp.ui.movie_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import my.app.moviesapp.ui.util.Dimens
import my.app.moviesapp.ui.util.Strings

@Composable
fun LabeledText(
    label: String,
    text: String?,
    onLinkClick: () -> Unit = {},
    backgroundColor: Color = Color.Gray
) {
    Column(
        modifier = Modifier.background(backgroundColor).padding(Dimens.dimen8)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = text ?: Strings.NotAvailable,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.clickable { onLinkClick() }
        )
    }
}