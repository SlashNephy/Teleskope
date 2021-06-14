package blue.starry.teleskope.components

import androidx.compose.desktop.SwingPanel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import blue.starry.teleskope.api.TeleskopeUserAgent
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent

@Composable
internal actual fun MediaPlayerImpl(url: String, volume: Int, modifier: Modifier) {
    println("Video player for $url")
    NativeDiscovery().discover()
    
    // Doesn't work on macOS, see https://github.com/caprica/vlcj/issues/887 for suggestions.
    val mediaPlayerComponent = remember {
        EmbeddedMediaPlayerComponent(
            MediaPlayerFactory(
                // For privacy, see https://github.com/caprica/vlcj#privacy-considerations for details
                "--no-metadata-network-access"
            ).apply {
                application().setUserAgent(TeleskopeUserAgent, TeleskopeUserAgent)
            },
            null, null, null, null
        )
    }

    SideEffect {
        println("play = ${mediaPlayerComponent.mediaPlayer().media().play(url)}")
    }
    
    SideEffect {
        println("setVolume = ${mediaPlayerComponent.mediaPlayer().audio().setVolume(volume)}")
    }
    
    DisposableEffect(mediaPlayerComponent) {
        onDispose {
            println("Disposing mediaPlayerComponent")
            mediaPlayerComponent.release()
        }
    }
    
    SwingPanel(
        background = Color.Black,
        modifier = modifier,
        factory = {
            mediaPlayerComponent
        }
    )
}
