package my.app.moviesapp.ui.navigation

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import my.app.moviesapp.MainActivityViewModel
import my.app.moviesapp.ui.search.SearchMoviesViewModel
import my.app.moviesapp.ui.util.Dimens
import my.app.moviesapp.ui.util.Strings
import my.app.moviesapp.util.Const


@Composable
fun TopBar(
    navController: NavHostController,
    searchMoviesViewModel: SearchMoviesViewModel = hiltViewModel(),
    mainActivityViewModel: MainActivityViewModel = hiltViewModel()
) {
    val query by searchMoviesViewModel.currentQuery.collectAsState()
    val currentRoute by mainActivityViewModel.currentRoute.collectAsState()

    TopAppBarPreview(currentRoute, query, doSearching = { query ->
        println("KKKKKK ")
        searchMoviesViewModel.doSearching()
    }, updateQuery = { query ->
        searchMoviesViewModel.updateQuery(query)
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
    doSearching: (String) -> Unit = {},
    updateQuery: (String) -> Unit = {},
    navigateUp: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            when (currentScreen) {
                //search bar for movies
                Screens.SearchScreen -> {
                    SearchBar(
                        modifier = Modifier.offset(y = Dimens.dimenNegative5), inputField = {
                            SearchBarDefaults.InputField(
                                placeholder = { Text(Strings.Search) },
                                query = query,
                                onQueryChange = { query -> updateQuery(query) },
                                onSearch = { query -> doSearching(query) },
                                onExpandedChange = {},
                                expanded = false
                            )
                        },
                        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.onPrimary),
                        expanded = false,
                        onExpandedChange = {},
                        content = {})
                }

                else -> {
                    Text(currentScreen?.title ?: "", color = Color.White)
                }
            }
        }, navigationIcon = {
            //show arrow if screen is not in bottom bar
            if (!Const.bottomBarScreens.contains(currentScreen)) {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        })
}