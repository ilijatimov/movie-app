package my.app.moviesapp.ui.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import my.app.moviesapp.data.model.movie_details.MovieGenres
import my.app.moviesapp.data.model.movie_details.ProductionCompanies
import my.app.moviesapp.ui.navigation.Screens
import java.text.SimpleDateFormat
import java.util.Locale

fun setBudget(budget: Int?) : String {
    return if (budget != 0 && budget != null) {
        Strings.budgetDollar(budget)
    } else {
        Strings.NotAvailable
    }
}


fun setRuntime(runtime: Int?) : String {
    return if (runtime != 0 && runtime != null) {
        Strings.minutes(runtime)
    }
    else{
        Strings.NotAvailable
    }
}


fun setReleaseDate(releaseDate: String?) : String {
    return if (!releaseDate.isNullOrEmpty()) {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(releaseDate)!!
        SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(date)
    } else {
        Strings.NotAvailable
    }
}

fun setGenresListAsText(genresList: List<MovieGenres>?) : String {
    var genresString = ""
    return if (!genresList.isNullOrEmpty()) {
        genresList.let {
            //add a new line of text for each item
            for (i in it.indices) {
                val item = it[i]
                genresString += item.name

                if (i != it.size - 1) {
                    genresString += "\n"
                }
            }
        }
        genresString
    } else {
        Strings.NotAvailable
    }


}


fun setProductionCompaniesListAsText(pcList: List<ProductionCompanies>?) : String {
    var pcString = ""
    return if (!pcList.isNullOrEmpty()) {
        pcList.let {
            //add a new line of text for each item
            for (i in it.indices) {
                val item = it[i]
                pcString += item.name

                if (i != it.size - 1) {
                    pcString += "\n"
                }
            }
        }
        pcString
    } else {
        Strings.NotAvailable
    }
}


fun setText(textString: String?) : String {
    return if (!textString.isNullOrEmpty()) {
        textString
    } else {
        Strings.NotAvailable
    }
}

fun openLinkInBrowser(url: String?, context: Context) {
    try {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        context.startActivity(i)
    } catch (e: java.lang.Exception) {
    }
}

suspend fun SnackbarHostState.showSnackBar(message : String){
    currentSnackbarData?.dismiss()
    showSnackbar(
        message = message,
        duration = SnackbarDuration.Short
    )
}

fun NavController.popupToDestination(screen : Screens){
    navigate(screen.route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}