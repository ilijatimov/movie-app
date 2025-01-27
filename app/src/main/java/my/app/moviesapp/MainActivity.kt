package my.app.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import my.app.moviesapp.theme.MoviesAppTheme
import my.app.moviesapp.ui.navigation.BottomBar
import my.app.moviesapp.ui.navigation.NavigationGraph
import my.app.moviesapp.ui.navigation.TopBar
import my.app.moviesapp.ui.navigation.getCurrentScreen
import my.app.moviesapp.ui.search.SearchMoviesViewModel
import my.app.moviesapp.util.Const

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                val navController = rememberNavController()
                val mainActivityViewModel: MainActivityViewModel = hiltViewModel()
                //this viewmodel is passed down to TopBar and the screen for searching movies
                val searchMoviesViewModel: SearchMoviesViewModel = hiltViewModel()


                val currentRoute by mainActivityViewModel.currentRoute.collectAsState()

                // set current screen that is observed in TopBar, BottomBar and MainActivity, in order
                // to set the title ,set the selected navigation item, and to show the bottombar
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    val currentScreen = getCurrentScreen(destination.route)
                    mainActivityViewModel.setCurrentScreen(currentScreen)
                }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(navController)
                    }, bottomBar = {
                        if(Const.bottomBarScreens.contains(currentRoute)){
                            BottomBar(navController)
                        }
                    }) { innerPadding ->
                    NavigationGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController,
                        searchMoviesViewModel = searchMoviesViewModel
                    )
                }
            }
        }
    }
}