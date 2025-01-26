package my.app.moviesapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import my.app.moviesapp.data.repository.search.SearchMoviesRepository
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val repository: SearchMoviesRepository
) : ViewModel() {

    val query = MutableStateFlow("")

    //transform flow
    @OptIn(ExperimentalCoroutinesApi::class)
    val movies = query.flatMapLatest { query ->
        repository.searchMoviesList(query).cachedIn(viewModelScope)
    }

    fun doSearching(queryString: String) {
        viewModelScope.launch {
            query.emit(queryString)
        }
    }
}