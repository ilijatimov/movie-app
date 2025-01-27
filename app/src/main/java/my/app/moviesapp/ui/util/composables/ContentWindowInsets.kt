package my.app.moviesapp.ui.util.composables

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable

@Composable
fun contentWindowInsets() = WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal)