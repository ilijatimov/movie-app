package my.app.moviesapp.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.networking.NetworkService
import my.app.moviesapp.util.Const

class SearchMoviesPagingSource(
    private val networkService: NetworkService,
    private val query: String
) : PagingSource<Int, MovieDetails>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetails> {
        val position = params.key ?: Const.STARTING_INDEX

        return try{
            val response = networkService.searchMovies(Const.API_KEY,position, query)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == Const.STARTING_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieDetails>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }
}