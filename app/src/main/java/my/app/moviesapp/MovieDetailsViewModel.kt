package my.app.moviesapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import my.app.moviesapp.ui.navigation.Screens
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private var _currentScreen = MutableStateFlow<Screens?>(null)
    val currentRoute = _currentScreen.asStateFlow()

    fun setCurrentScreen(screen : Screens){
        _currentScreen.value = screen
    }
}