package blue.starry.teleskope.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MediaPlayer(url: String, volume: Int, modifier: Modifier) {
    MediaPlayerImpl(url, volume, modifier)
}

internal expect fun MediaPlayerImpl(url: String, volume: Int, modifier: Modifier)
