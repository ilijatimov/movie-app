package my.app.moviesapp.ui.util

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import my.app.moviesapp.data.model.movie_details.MovieDetails
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) : ViewModel()
{
    fun setMovieDetails(movieDetails: MovieDetails?) {
        savedStateHandle["movie_details"] = movieDetails
    }

    fun getMovieDetails(): MovieDetails? {
        return savedStateHandle["movie_details"]
    }
}