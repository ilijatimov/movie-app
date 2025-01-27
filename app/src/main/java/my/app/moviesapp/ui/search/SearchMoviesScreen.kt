package my.app.moviesapp.ui.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import my.app.moviesapp.data.model.movie_details.MovieDetails

@Composable
fun SearchMoviesScreen(
    searchViewModel: SearchMoviesViewModel = hiltViewModel(),
    onMovieClick: (MovieDetails?) -> Unit
) {
    // Collect the paging items
    val moviePagingItems = searchViewModel.movies.collectAsLazyPagingItems()

    MoviesLazyColumn(moviePagingItems,onMovieClick)
}