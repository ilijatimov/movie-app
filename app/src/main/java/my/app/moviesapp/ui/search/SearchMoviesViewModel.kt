package my.app.moviesapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.data.repository.search.SearchMoviesRepository
import my.app.moviesapp.ui.navigation.Screens
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val repository: SearchMoviesRepository
) : ViewModel() {

    private val _currentQuery = MutableStateFlow("")
    val currentQuery: StateFlow<String> = _currentQuery.asStateFlow()

    private val _movies = MutableStateFlow<PagingData<MovieDetails>>(PagingData.empty())
    val movies: StateFlow<PagingData<MovieDetails>> = _movies.asStateFlow()

    // Update query without initiating a search
    fun updateQuery(queryString: String) {
        _currentQuery.value = queryString
    }

    // Initiate search with the current query
    fun doSearching() {
        viewModelScope.launch {
            repository.searchMoviesList(_currentQuery.value)
                .cachedIn(viewModelScope).collect { pagingData ->
                    _movies.value = pagingData
                }
        }
    }
}