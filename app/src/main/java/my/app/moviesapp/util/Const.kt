package my.app.moviesapp.util

import my.app.moviesapp.ui.navigation.Screens

object Const {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "e0c9a1b2deb6e90a7633597791ee9db0"
    const val BASE_URL_IMAGES = "https://image.tmdb.org/t/p/"
    const val SMALLER_IMAGE = "w154"
    const val LARGER_IMAGE = "w500"
    const val STARTING_INDEX = 1

    val bottomBarScreens = listOf(
        Screens.PopularScreen,
        Screens.FavouritesScreen,
        Screens.SearchScreen
    )

}