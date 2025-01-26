package my.app.moviesapp.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.data.repository.popular.PopularListRepository
import javax.inject.Inject

@HiltViewModel
class PopularListViewModel @Inject constructor(private val movieRepository: PopularListRepository): ViewModel() {

    //get the flow of paging data
    fun getMovieList():Flow<PagingData<MovieDetails>>{
        return movieRepository.getMoviesList().cachedIn(viewModelScope)
    }
}