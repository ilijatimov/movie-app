package my.app.moviesapp.data.model.popular

import my.app.moviesapp.data.model.movie_details.MovieDetails

data class ListResponse(
    val results : List<MovieDetails>,
    val total_results : Int,
    val total_pages : Int
)