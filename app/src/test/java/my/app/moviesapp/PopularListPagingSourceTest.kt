package my.app.moviesapp

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.data.model.popular.ListResponse
import my.app.moviesapp.networking.NetworkService
import my.app.moviesapp.ui.popular.PopularListPagingSource
import my.app.moviesapp.util.Const
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PopularListPagingSourceTest {

    private val mockMovies = listOf(
        MovieDetails(id = 1, title = "Movie 1"),
        MovieDetails(id = 2, title = "Movie 2")
    )

    @MockK
    lateinit var fakeNetworkService : NetworkService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun getFirstPAgeOfMovies() = runTest {
        // mock the network api call to get first page of movies
        coEvery { fakeNetworkService.getMoviesList(Const.API_KEY,1) } returns ListResponse(
            results = mockMovies,
            total_results = mockMovies.size,
            total_pages = 1
        )

        val pagingSource = PopularListPagingSource(fakeNetworkService)

        val pager = TestPager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSource = pagingSource
        )

        val result = pager.refresh() as PagingSource.LoadResult.Page

        // chekc if correct list is returned
        assertTrue(result.data.isNotEmpty())
        assertTrue(result.data.size == mockMovies.size)
        assertTrue(result.data.containsAll(mockMovies))
    }
}