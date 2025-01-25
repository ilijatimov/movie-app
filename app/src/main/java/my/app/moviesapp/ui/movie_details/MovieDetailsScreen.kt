package my.app.moviesapp.ui.movie_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.util.Resource
import my.app.moviesapp.ui.util.SharedViewModel
import my.app.moviesapp.ui.util.Strings
import my.app.moviesapp.ui.util.composables.ProgressIndicator
import my.app.moviesapp.ui.util.showSnackBar

@Composable
fun MovieDetailsScreen(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    viewModelDetails: DetailsViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val movieDetailsResponse = viewModelDetails.movieDetailsResponse.collectAsState()
    val currentStatus = movieDetailsResponse.value?.status
    // Fetch movie details
    LaunchedEffect(Unit) {
        val movieDetails = sharedViewModel.getMovieDetails()
        val movieId = movieDetails?.id

        viewModelDetails.getMovieDetails(movieId)
    }

    Scaffold(
        contentWindowInsets = WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        floatingActionButton = {
            if (currentStatus != Resource.Status.LOADING) {
                FABAddToFavourites(snackBarHostState = snackBarHostState)
            }
        }
    ) { _ ->
            Box(modifier = Modifier.fillMaxSize()) {
                when (currentStatus) {
                    Resource.Status.ERROR -> {
                        val fallbackMovieDetails = sharedViewModel.getMovieDetails()
                        viewModelDetails.setSelectedMovie(fallbackMovieDetails)

                        LaunchedEffect(Unit) {
                            snackBarHostState.showSnackBar(Strings.ErrorFetchingData)
                        }
                    }

                    Resource.Status.LOADING -> {
                        ProgressIndicator(Modifier.align(Alignment.Center))
                    }

                    else -> {}
                }
                if (currentStatus != Resource.Status.LOADING) {
                    MovieDetailsContent(viewModelDetails.movieDetails)
                    viewModelDetails.checkIfMovieSaved()
                }
            }
        }
    }


