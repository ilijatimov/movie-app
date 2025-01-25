package my.app.moviesapp.ui.navigation

import my.app.moviesapp.R
import my.app.moviesapp.ui.util.Strings

sealed class Screens(
    val route: String,
    var title: String,
    val icon: Int? = null
) {
    data object FavouritesScreen : Screens("favourites", Strings.Favorites, R.drawable.ic_favorite_24)
    data object PopularScreen : Screens("popular", Strings.Popular, R.drawable.ic_movie_24)
    data object SearchScreen : Screens("search", Strings.Search, R.drawable.ic_search_24)
    data object MovieDetailsScreen : Screens("details", Strings.Details)
}

fun getCurrentScreen(currentRoute: String?): Screens {
    return when {
        currentRoute?.startsWith(Screens.FavouritesScreen.route) == true -> Screens.FavouritesScreen
        currentRoute?.startsWith(Screens.PopularScreen.route) == true -> Screens.PopularScreen
        currentRoute?.startsWith(Screens.SearchScreen.route)  == true -> Screens.SearchScreen
        currentRoute?.startsWith(Screens.MovieDetailsScreen.route)  == true -> Screens.MovieDetailsScreen
        else -> throw IllegalArgumentException("Unknown route: $currentRoute")
    }
}
