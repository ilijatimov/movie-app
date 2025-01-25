package my.app.moviesapp.data.repository.popular

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieapp.networking.NetworkService
import my.app.moviesapp.ui.popular.PopularListPagingSource
import javax.inject.Inject

class PopularListRepository @Inject constructor(
    private val service: NetworkService
) {
    fun getMoviesList() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularListPagingSource(service) }
        ).flow
}