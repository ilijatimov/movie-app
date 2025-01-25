package my.app.moviesapp.ui.movie_details

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import my.app.moviesapp.R
import my.app.moviesapp.ui.util.Strings
import my.app.moviesapp.ui.util.showSnackBar

@Composable

fun FABAddToFavourites(
    viewModelDetails: DetailsViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState
){
    val isMovieSaved by viewModelDetails.isMovieSaved.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    FABAddToFavouritesPreview(fabClick = {
        if (isMovieSaved) {
            viewModelDetails.deleteFavoriteMovie()
            coroutineScope.launch {
                snackBarHostState.showSnackBar(Strings.MovieRemovedFromFavorites)
            }

        } else {
            viewModelDetails.saveFavoriteMovie()
            coroutineScope.launch {
                snackBarHostState.showSnackBar(Strings.MovieAddedToFavorites)
            }
        }
    }, setIcon = {
        if (isMovieSaved) R.drawable.ic_favorite_24 else R.drawable.ic_not_favorite_24
    })
}

@Composable
@Preview
fun FABAddToFavouritesPreview(fabClick:() -> Unit = {}, setIcon : () -> Int = { R.drawable.ic_not_favorite_24 }){
    FloatingActionButton(onClick = {
        fabClick()
    }) {
        Icon(
            painter = painterResource(id = setIcon()),
            contentDescription = null
        )
    }
}