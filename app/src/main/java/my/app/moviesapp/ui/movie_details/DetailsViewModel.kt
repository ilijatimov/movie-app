package my.app.moviesapp.ui.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.movie_details.MovieDetails
import com.example.movieapp.data.repository.movie_details.MovieDetailsRepository
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: MovieDetailsRepository): ViewModel() {


    private val _movieDetailsResponse = MutableStateFlow<Resource<MovieDetails?>?>(null)
    val movieDetailsResponse = _movieDetailsResponse.asStateFlow()

    private var _isMovieSaved = MutableStateFlow(false)
    val isMovieSaved = _isMovieSaved.asStateFlow()

    var movieDetails : MovieDetails? = null

    //save movie to db
    fun saveFavoriteMovie() = viewModelScope.launch {
        movieDetails?.let { movieDetails->
            repository.addToFavorite(movieDetails = movieDetails)
            checkIfMovieSaved()
        }

    }
    //delete movie from db
    fun deleteFavoriteMovie() = viewModelScope.launch {
        movieDetails?.let { movieDetails->
            repository.deleteFromFavorite(movieDetails = movieDetails)
            checkIfMovieSaved()
        }
    }
    //check if movie is already in db
    fun checkIfMovieSaved() = viewModelScope.launch(Dispatchers.IO) {
        _isMovieSaved.value = repository.isMovieAlreadySaved(movieDetails) >= 1
    }
    //network call for movie details
    fun getMovieDetails(movieId : Int?){
        viewModelScope.launch {
            _movieDetailsResponse.value = Resource.loading()

            val response = try {
                repository.getMovieDetails(movieId).let {
                    if (it.isSuccessful) {
                        setSelectedMovie(it.body())
                        Resource.success(it.body()!!)
                    } else {
                        Resource.error()
                    }
                }
            } catch (e: Exception) {
                Resource.error()
            }
            _movieDetailsResponse.value = response
        }
    }

    fun setSelectedMovie(movieDetails : MovieDetails?){
        this.movieDetails = movieDetails
    }
}