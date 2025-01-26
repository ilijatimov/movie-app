package my.app.moviesapp.data.repository.favorites

import kotlinx.coroutines.flow.Flow
import my.app.moviesapp.data.db.MoviesDao
import my.app.moviesapp.data.model.movie_details.MovieDetails
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
   private val moviesDao: MoviesDao
) {
    fun getFavoriteMovies(): Flow<List<MovieDetails>> = moviesDao.getFavouriteMovies()
    fun updateNoteForMovie(movieId:Int,note:String?) = moviesDao.updateNoteForMovie(movieId,note)
}