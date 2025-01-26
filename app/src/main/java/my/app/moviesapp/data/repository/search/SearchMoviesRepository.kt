package my.app.moviesapp.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import my.app.moviesapp.networking.NetworkService
import my.app.moviesapp.ui.search.SearchMoviesPagingSource
import javax.inject.Inject

class SearchMoviesRepository @Inject constructor(
    private val service: NetworkService
) {
    fun searchMoviesList(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchMoviesPagingSource(service, query) }
        ).flow
}