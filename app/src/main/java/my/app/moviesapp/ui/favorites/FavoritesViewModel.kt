package my.app.moviesapp.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import my.app.moviesapp.data.model.movie_details.MovieDetails
import my.app.moviesapp.data.repository.favorites.FavoritesRepository
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: FavoritesRepository) :
    ViewModel() {

    // Expose movies as a StateFlow
    val favouriteMovies: StateFlow<List<MovieDetails>> = repository.getFavoriteMovies()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly, // Eagerly starts collecting the flow
            initialValue = emptyList() // Initial state for the StateFlow
        )

    // Function to update the note for a movie
    fun updateNoteForMovie(movieId: Int, note: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNoteForMovie(movieId, note)
        }
    }
}