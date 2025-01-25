package my.app.moviesapp.ui.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.data.model.movie_details.MovieDetails
import kotlinx.coroutines.launch
import my.app.moviesapp.ui.popular.PopularListViewModel
import my.app.moviesapp.ui.util.Strings
import my.app.moviesapp.ui.util.composables.EmptyList
import my.app.moviesapp.ui.util.composables.MovieItem
import my.app.moviesapp.ui.util.composables.ProgressIndicator
import my.app.moviesapp.ui.util.composables.RetryLayout

@Composable
fun SearchMoviesScreen(
    popularMovies: SearchMoviesViewModel = hiltViewModel(),
    onMovieClick: (MovieDetails?) -> Unit
) {
    // Collect the paging items
    val moviePagingItems = popularMovies.movies.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val coroutine = rememberCoroutineScope()

    LazyColumn(state = listState) {
        items(moviePagingItems.itemCount) { index ->
            val movieDetails = moviePagingItems[index]
            MovieItem(movieDetails) {
                onMovieClick(movieDetails)
            }
        }

        moviePagingItems.apply {
            when {
                itemCount == 0 ->{
                    item {
                        EmptyList(Strings.NoSearchResults,Modifier.fillParentMaxSize())
                    }
                }
                loadState.refresh is LoadState.NotLoading ->{
                    coroutine.launch {
                        listState.scrollToItem(0)
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    item {
                        RetryLayout(Modifier.fillParentMaxSize()) {
                            moviePagingItems.refresh()
                        }
                    }
                }

                loadState.refresh is LoadState.Loading -> {
                    item {
                        ProgressIndicator(Modifier.fillParentMaxSize())
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        ProgressIndicator(Modifier.fillParentMaxWidth())
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        RetryLayout {
                            moviePagingItems.retry()
                        }
                    }
                }
            }
        }
    }
}