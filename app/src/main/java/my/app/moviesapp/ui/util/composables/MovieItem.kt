package my.app.moviesapp.ui.util.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import my.app.moviesapp.R
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.ui.util.Dimens
import my.app.moviesapp.ui.util.Strings
import my.app.moviesapp.util.Const

@Composable
@Preview
fun MovieItem(
    movieDetails: MovieDetails? = null,
    showNoteIcon: Boolean = false,
    onNoteIconClick: () -> Unit = {},
    onMovieDetailsClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.dimen6).clickable { onMovieDetailsClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.dimen4
        )
    ) {
        Row(
            modifier = Modifier.padding(end = Dimens.dimen6)
                .fillMaxWidth()
        ) {
            // Movie Poster
            LoadImage(Const.SMALLER_IMAGE,movieDetails?.poster_path,Modifier
                .size(
                    width = Dimens.dimen140,
                    height = Dimens.dimen170
                ))

            Spacer(modifier = Modifier.width(Dimens.dimen6))

            Column(
                modifier = Modifier
                    .weight(1f)

            ) {
                // Movie Title
                Text(
                    color = MaterialTheme.colorScheme.primary,
                    text = movieDetails?.title ?: Strings.MovieTitle,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = Dimens.dimen20)
                )

                Spacer(modifier = Modifier.height(Dimens.dimen4))

                // Movie Description
                Text(
                    text = movieDetails?.overview ?: Strings.Description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Note Icon
            if (showNoteIcon) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sticky_note_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = Dimens.dimen20)
                        .clickable { onNoteIconClick() }
                )
            }
        }
    }
}
