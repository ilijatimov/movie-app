package my.app.moviesapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import my.app.moviesapp.data.db.MoviesDao
import my.app.moviesapp.data.db.MoviesDatabase
import my.app.moviesapp.data.model.movie_details.MovieDetails
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class MoviesDaoTest {

    private lateinit var database: MoviesDatabase
    private lateinit var moviesDao: MoviesDao

    @BeforeEach
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java)
            .build()
        moviesDao = database.getMoviesDao()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        database.close()
    }

    @Test
    fun shouldInsertAndFetchMovieDetails() = runTest {
        val movie = MovieDetails(id = 1, title = "Movie 1")
        moviesDao.insert(movie)

        // Fetching the movies list
        val result = moviesDao.getFavouriteMovies().first()

        // Assert the movie is present in the result
        assertTrue(result.contains(movie))
    }

    @Test
    fun shouldUpdateMovieNote() = runTest {
        val movie = MovieDetails(id = 1, title = "Movie 1", note = "Old Note")
        moviesDao.insert(movie)

        // Updating the note for the movie
        val updatedNote = "Updated Note"
        moviesDao.updateNoteForMovie(movie.id!!, updatedNote)

        // Fetching the updated movie and asserting if the note is updated
        val updatedMovie = moviesDao.getFavouriteMovies().first().first { it.id == movie.id }
        assertEquals(updatedNote, updatedMovie.note)
    }

    @Test
    fun shouldDeleteMovieFromDatabase() = runTest {
        val movie = MovieDetails(id = 1, title = "Movie 1")
        moviesDao.insert(movie)

        // Deleting the movie from the database
        moviesDao.delete(movie)

        // Fetching the movies list and asserting if the movie is removed
        val result = moviesDao.getFavouriteMovies().first()
        assertFalse(result.contains(movie))
    }
}
