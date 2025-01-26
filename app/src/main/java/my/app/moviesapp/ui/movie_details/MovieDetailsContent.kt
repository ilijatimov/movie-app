package my.app.moviesapp.ui.movie_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.ui.util.Dimens
import my.app.moviesapp.ui.util.Strings
import my.app.moviesapp.ui.util.composables.LoadImage
import my.app.moviesapp.ui.util.openLinkInBrowser
import my.app.moviesapp.ui.util.setBudget
import my.app.moviesapp.ui.util.setGenresListAsText
import my.app.moviesapp.ui.util.setProductionCompaniesListAsText
import my.app.moviesapp.ui.util.setReleaseDate
import my.app.moviesapp.ui.util.setRuntime
import my.app.moviesapp.util.Const

@Composable
@Preview
fun MovieDetailsContent(movieDetails: MovieDetails? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val context= LocalContext.current
        //show poster with glide
        LoadImage(
            url = movieDetails?.poster_path,
            imageSize = Const.LARGER_IMAGE,
            modifier = Modifier.fillMaxWidth().height(Dimens.dimen400)
        )

        // movie data in a column
        Column {
            LabeledText(label = Strings.MovieTitle, text = movieDetails?.title)
            LabeledText(label = Strings.Description, text = movieDetails?.overview, backgroundColor = Color.Transparent)
            LabeledText(
                label = Strings.Budget,
                text = setBudget(movieDetails?.budget)
            )
            LabeledText(
                label = Strings.Genres,
                text = setGenresListAsText(movieDetails?.genres), backgroundColor = Color.Transparent
            )
            LabeledText(
                label = Strings.Homepage,
                text = movieDetails?.homepage,
                onLinkClick = {
                    openLinkInBrowser(movieDetails?.homepage,context)
                }
            )
            LabeledText(label = Strings.Released, text = setReleaseDate(movieDetails?.release_date),backgroundColor = Color.Transparent)
            LabeledText(
                label = Strings.ProductionCompanies,
                text = setProductionCompaniesListAsText(movieDetails?.production_companies)
            )
            LabeledText(label = Strings.Runtime, text = setRuntime(movieDetails?.runtime), backgroundColor = Color.Transparent)
            LabeledText(label = Strings.Status, text = movieDetails?.status)
        }
    }
}