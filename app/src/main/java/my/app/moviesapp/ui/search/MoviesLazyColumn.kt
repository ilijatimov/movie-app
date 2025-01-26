package my.app.moviesapp.ui.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.launch
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.ui.util.Strings
import my.app.moviesapp.ui.util.composables.EmptyList
import my.app.moviesapp.ui.util.composables.MovieItem
import my.app.moviesapp.ui.util.composables.ProgressIndicator
import my.app.moviesapp.ui.util.composables.RetryLayout

@Composable
fun MoviesLazyColumn(
    moviePagingItems: LazyPagingItems<MovieDetails>,
    onMovieClick: (MovieDetails?) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutine = rememberCoroutineScope()

    when (moviePagingItems.loadState.refresh) {
        // Show progress indicator during the initial load
        is LoadState.Loading -> ProgressIndicator(Modifier.fillMaxSize())

        // Show retry layout if the initial load fails
        is LoadState.Error -> RetryLayout(Modifier.fillMaxSize()) {
            moviePagingItems.retry()
        }

        // Show movies list or empty state if the load succeeds
        is LoadState.NotLoading -> {
            if (moviePagingItems.itemCount == 0) {
                EmptyList(Strings.NoSearchResults, Modifier.fillMaxSize())
            } else {
                // Scroll to the top to show search results from the beginning
                LaunchedEffect(Unit){
                    coroutine.launch { listState.scrollToItem(0) }
                }
                //populate list
                PopulateList(moviePagingItems, onMovieClick, listState)
            }
        }
    }
}

@Composable
fun PopulateList(
    moviePagingItems: LazyPagingItems<MovieDetails>,
    onMovieClick: (MovieDetails?) -> Unit,
    listState: LazyListState
) {
    LazyColumn(state = listState) {
        items(moviePagingItems.itemCount) { index ->
            val movieDetails = moviePagingItems[index]
            MovieItem(movieDetails, onMovieDetailsClick = { onMovieClick(movieDetails) })
        }

        HandleAppendState(moviePagingItems)
    }
}


private fun LazyListScope.HandleAppendState(moviePagingItems: LazyPagingItems<MovieDetails>) {
    when (moviePagingItems.loadState.append) {
        // Show progress indicator at the bottom during pagination
        is LoadState.Loading -> item {
            ProgressIndicator(Modifier.fillMaxSize())
        }

        // Show retry button at the bottom if pagination fails
        is LoadState.Error -> item {
            RetryLayout {
                moviePagingItems.retry()
            }
        }

        else -> Unit
    }
}
