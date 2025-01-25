package my.app.moviesapp.ui.util.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.movieapp.util.Const
import my.app.moviesapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(imageSize: String, url: String?, modifier: Modifier = Modifier) {
    GlideImage(
        model = "${Const.BASE_URL_IMAGES}$imageSize$url",
        contentDescription = null,
        modifier = modifier,
        failure = placeholder(R.drawable.ic_close_24),
        loading = placeholder(R.drawable.ic_close_24)
    )
}