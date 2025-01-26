package my.app.moviesapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import my.app.moviesapp.MainActivityViewModel
import my.app.moviesapp.ui.util.popupToDestination
import my.app.moviesapp.util.Const

@Composable
@Preview
fun BottomBar(
    navController: NavController? = null,
    mainActivityViewModel: MainActivityViewModel = hiltViewModel()
) {

    val currentScreen by mainActivityViewModel.currentRoute.collectAsState()

    NavigationBar {
        Const.bottomBarScreens.forEach { screen ->
            val selected = currentScreen == screen
            NavigationBarItem(
                label = { Text(text = screen.title) },
                icon = {
                    screen.icon?.let { icon ->
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = screen.title
                        )
                    }
                },
                selected = selected,
                onClick = {
                    //pop to a destination without saving the previous one in the backstack
                    if (!selected) {
                        navController?.popupToDestination(screen)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    }
}
