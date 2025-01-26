package my.app.moviesapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.ui.favorites.FavoritesScreen
import my.app.moviesapp.ui.movie_details.MovieDetailsScreen
import my.app.moviesapp.ui.popular.PopularScreen
import my.app.moviesapp.ui.search.SearchMoviesScreen
import my.app.moviesapp.ui.search.SearchMoviesViewModel
import my.app.moviesapp.ui.util.SharedViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier,
    navController: NavHostController,
    sharedViewModel: SharedViewModel = hiltViewModel(),
    searchMoviesViewModel: SearchMoviesViewModel = hiltViewModel()
) {
    Box(modifier = modifier) {
        NavHost(
            navController = navController,
            startDestination = Screens.PopularScreen.route
        ) {

            val openMovieDetailsCallback :(MovieDetails?) -> Unit = { movieDetails->
                sharedViewModel.setMovieDetails(movieDetails)
                navController.navigate(Screens.MovieDetailsScreen.route)
            }

            composable(Screens.PopularScreen.route) {
                PopularScreen(onMovieClick = openMovieDetailsCallback)
            }

            composable(Screens.SearchScreen.route) {
                SearchMoviesScreen(searchMoviesViewModel,onMovieClick = openMovieDetailsCallback)
            }

            composable(Screens.FavouritesScreen.route) {
                FavoritesScreen(onMovieClick = openMovieDetailsCallback)
            }

            composable(Screens.MovieDetailsScreen.route) {
                MovieDetailsScreen(sharedViewModel)
            }
        }
    }
}