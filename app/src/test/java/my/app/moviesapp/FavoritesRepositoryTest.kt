package my.app.moviesapp

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import my.app.moviesapp.data.db.MoviesDao
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.data.repository.favorites.FavoritesRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class FavoritesRepositoryTest {

    @MockK
    lateinit var moviesDao: MoviesDao

    private lateinit var favoritesRepository: FavoritesRepository

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        favoritesRepository = FavoritesRepository(moviesDao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun shouldReturnFavouriteMovies() = runTest {
        val expectedMovies = listOf(MovieDetails(id = 1, title = "Movie 1"))
        every { moviesDao.getFavouriteMovies() } returns flowOf(expectedMovies)
        //get the first list of favourite movies from db
        val result = favoritesRepository.getFavoriteMovies().first()
        //check if we get the expected result
        assertEquals(expectedMovies, result)
    }

    @Test
    fun shouldUpdateNoteForMovie() = runTest {
        val movieId = 1
        val note = "Great movie!"
        // Mock the behavior of moviesDao to return a success indicator (e.g., 1)
        every { moviesDao.updateNoteForMovie(movieId, note) } returns 1

        favoritesRepository.updateNoteForMovie(movieId, note)
        // Verify that moviesDao's updateNoteForMovie method was called with the correct parameters
        verify { moviesDao.updateNoteForMovie(movieId, note) }
    }
}



