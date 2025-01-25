package com.example.movieapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.model.movie_details.MovieDetails
import kotlinx.coroutines.flow.Flow


@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getFavouriteMovies(): Flow<List<MovieDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetails: MovieDetails)

    @Delete
    suspend fun delete(movieDetails: MovieDetails)

    @Query("SELECT COUNT(*) FROM movies WHERE id = :movieId")
    fun isMovieAlreadySaved(movieId:Int?) : Int
    //update the note for the movie with id
    @Query("UPDATE movies SET note = :note where id = :movieId")
    fun updateNoteForMovie(movieId: Int, note: String?): Int
}