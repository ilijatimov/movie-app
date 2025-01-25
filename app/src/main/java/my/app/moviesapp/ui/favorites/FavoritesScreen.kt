package my.app.moviesapp.ui.favorites

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.data.model.movie_details.MovieDetails
import com.example.movieapp.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.launch
import my.app.moviesapp.ui.util.composables.EmptyList
import my.app.moviesapp.ui.util.composables.MovieItem
import my.app.moviesapp.ui.util.Strings
import my.app.moviesapp.ui.util.showSnackBar

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    onMovieClick: (MovieDetails?) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var selectedMovie by rememberSaveable { mutableStateOf<MovieDetails?>(null) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val favouriteMovies by favoritesViewModel.favouriteMovies.collectAsState()

    Scaffold(
        contentWindowInsets = WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ){ _ ->
        if(favouriteMovies.isEmpty()){
            EmptyList(Strings.NoFavorites)
        }
        else{
            LazyColumn {
                items(favouriteMovies) { movieDetails ->
                    MovieItem(movieDetails,true, onMovieDetailsClick = {
                        onMovieClick(movieDetails)
                    }, onNoteIconClick = {
                        selectedMovie = movieDetails
                        showDialog = true
                    })
                }
            }
        }


    }


    if (showDialog) {
        NoteEditDialog(
            movieDetails = selectedMovie,
            onEdit = { updatedNote, movieId ->
                movieId?.let {
                    favoritesViewModel.updateNoteForMovie(movieId, updatedNote)
                    showDialog = false
                    coroutineScope.launch {
                        snackBarHostState.showSnackBar(Strings.NoteUpdated)
                    }
                }
            },
            onCancel = {
                showDialog = false
            }
        )
    }
}