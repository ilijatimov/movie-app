package my.app.moviesapp.networking

import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.data.model.popular.ListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val MOVIE = "movie"
const val SEARCH = "search"

interface NetworkService {

    //Popular Movies list
    @GET("$MOVIE/popular")
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): ListResponse

    //Get Movie details
    @GET("$MOVIE/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String,
    ): Response<MovieDetails>

    //Search movies
    @GET("$SEARCH/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): ListResponse
}