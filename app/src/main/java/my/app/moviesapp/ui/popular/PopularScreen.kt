package my.app.moviesapp.ui.popular

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.ui.search.MoviesLazyColumn


@Composable
fun PopularScreen(
    popularMovies: PopularListViewModel = hiltViewModel(),
    onMovieClick: (MovieDetails?) -> Unit
) {
    // Collect the paging items
    val moviePagingItems = popularMovies.getMovieList().collectAsLazyPagingItems()
    MoviesLazyColumn(moviePagingItems,onMovieClick)
}

