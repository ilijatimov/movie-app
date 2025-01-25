package my.app.moviesapp.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.util.Const
import my.app.moviesapp.MainActivityViewModel
import my.app.moviesapp.ui.search.SearchMoviesViewModel
import my.app.moviesapp.ui.util.Dimens
import my.app.moviesapp.ui.util.Strings


@Composable
fun TopBar(
    navController: NavHostController,
    searchMoviesViewModel: SearchMoviesViewModel = hiltViewModel(),
    mainActivityViewModel: MainActivityViewModel = hiltViewModel()
) {
    val query by searchMoviesViewModel.query.collectAsState()
    val currentRoute by mainActivityViewModel.currentRoute.collectAsState()

    TopAppBarPreview(currentRoute, query, setQuery = { query ->
        searchMoviesViewModel.doSearching(query)
    }, navigateUp = {
        navController.navigateUp()
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TopAppBarPreview(
    currentScreen: Screens? = null,
    query: String = "",
    setQuery: (String) -> Unit = {},
    navigateUp: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            when (currentScreen) {
                Screens.SearchScreen -> {
                    SearchBar(
                        modifier = Modifier.offset(y = Dimens.dimenNegative5), inputField = {
                            SearchBarDefaults.InputField(
                                placeholder = { Text(Strings.Search) },
                                query = query,
                                onQueryChange = { query -> setQuery(query) },
                                onSearch = { query -> setQuery(query) },
                                onExpandedChange = {},
                                expanded = false
                            )
                        },
                        expanded = false,
                        onExpandedChange = {},
                        content = {})
                }

                else -> {
                    Text(currentScreen?.title ?: "")
                }
            }
        }, navigationIcon = {
            if (!Const.bottomBarScreens.contains(currentScreen)) {
                IconButton(onClick = { navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        })
}