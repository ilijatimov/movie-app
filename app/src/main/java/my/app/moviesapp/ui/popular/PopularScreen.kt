package my.app.moviesapp.ui.popular

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.ui.util.composables.MovieItem
import my.app.moviesapp.ui.util.composables.ProgressIndicator
import my.app.moviesapp.ui.util.composables.RetryLayout


@Composable
fun PopularScreen(
    popularMovies: PopularListViewModel = hiltViewModel(),
    onMovieClick: (MovieDetails?) -> Unit
) {
    // Collect the paging items
    val moviePagingItems = popularMovies.getMovieList().collectAsLazyPagingItems()

    LazyColumn {
        items(moviePagingItems.itemCount) { index ->
            val movieDetails = moviePagingItems[index]
            MovieItem(movieDetails) {
                onMovieClick(movieDetails)
            }
        }

        moviePagingItems.apply {
            when {
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

