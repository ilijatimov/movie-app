package my.app.moviesapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import my.app.moviesapp.data.db.MoviesDao
import my.app.moviesapp.data.db.MoviesDatabase
import my.app.moviesapp.data.model.movie_details.MovieDetails
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesDaoTest {

    private lateinit var database: MoviesDatabase
    private lateinit var moviesDao: MoviesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java)
            .build()
        moviesDao = database.getMoviesDao()
    }

    @After
    fun tearDown() {
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
