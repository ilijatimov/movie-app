package my.app.moviesapp.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.model.movie_details.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import my.app.moviesapp.data.repository.popular.PopularListRepository
import javax.inject.Inject

@HiltViewModel
class PopularListViewModel @Inject constructor(private val movieRepository: PopularListRepository): ViewModel() {
    private val _uiState = MutableStateFlow<PagingData<MovieDetails>>(PagingData.empty())
    val uiState: StateFlow<PagingData<MovieDetails>> = _uiState.asStateFlow()

    //get the flow of paging data
    fun getMovieList():Flow<PagingData<MovieDetails>>{
        return movieRepository.getMoviesList().cachedIn(viewModelScope)
    }
}