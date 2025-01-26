package my.app.moviesapp.data.repository.movie_details

import my.app.moviesapp.data.db.MoviesDao
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.networking.NetworkService
import my.app.moviesapp.util.Const
import javax.inject.Inject

class MovieDetailsRepository@Inject constructor(
    private val service: NetworkService,
    private val moviesDao: MoviesDao
)  {
    suspend fun getMovieDetails(movieId : Int?) = service.getMovieDetails(movieId, Const.API_KEY)

    suspend fun addToFavorite(movieDetails: MovieDetails) = moviesDao.insert(movieDetails = movieDetails)
    suspend fun deleteFromFavorite(movieDetails: MovieDetails) = moviesDao.delete(movieDetails = movieDetails)
    fun isMovieAlreadySaved(movieDetails: MovieDetails?) = moviesDao.isMovieAlreadySaved(movieDetails?.id)
}