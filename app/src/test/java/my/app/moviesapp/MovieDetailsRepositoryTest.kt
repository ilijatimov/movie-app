package my.app.moviesapp

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import my.app.moviesapp.data.db.MoviesDao
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.data.repository.movie_details.MovieDetailsRepository
import my.app.moviesapp.networking.NetworkService
import my.app.moviesapp.util.Const
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExtendWith(MockKExtension::class)
class MovieDetailsRepositoryTest {

    @MockK
    lateinit var networkService: NetworkService

    @MockK
    lateinit var moviesDao: MoviesDao

    private lateinit var movieDetailsRepository: MovieDetailsRepository

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        movieDetailsRepository = MovieDetailsRepository(networkService, moviesDao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun shouldFetchMovieDetailsSuccessfully() = runTest {
        val movieId = 1
        val expectedMovie = MovieDetails(id = movieId, title = "Movie 1")
        val mockResponse = Response.success(expectedMovie)

        // Mock the response from the network service for a specific movie ID
        coEvery { networkService.getMovieDetails(movieId, Const.API_KEY) } returns mockResponse

        //  Call the repository method to fetch movie details
        val result = movieDetailsRepository.getMovieDetails(movieId).body()

        // Check if we get the expected movie as response
        assertEquals(expectedMovie, result)
    }
}
