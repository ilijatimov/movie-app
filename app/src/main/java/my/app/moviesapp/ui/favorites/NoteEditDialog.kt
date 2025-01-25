package my.app.moviesapp.ui.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.ui.util.Dimens
import my.app.moviesapp.ui.util.Strings

@Composable
@Preview
fun NoteEditDialog(
    movieDetails: MovieDetails? = null,
    onEdit: (String?,Int?) -> Unit = { _, _ -> },
    onCancel: () -> Unit = {}
) {
    var noteText by remember { mutableStateOf(movieDetails?.note) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = Strings.EditNote) },
        text = {
            Column {
                OutlinedTextField(
                    value = noteText?:"",
                    onValueChange = {
                        if (it.length <= 100) noteText = it // Limit maxLength to 100
                    },
                    label = { Text(text = Strings.Note) },
                    placeholder = { Text(text = Strings.Note) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimens.dimen20),
                    maxLines = 3,
                    singleLine = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onEdit(noteText,movieDetails?.id) }) {
                Text(text = Strings.Edit)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(text = Strings.Cancel)
            }
        }
    )
}
