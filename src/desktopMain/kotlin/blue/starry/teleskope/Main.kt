package blue.starry.teleskope

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import blue.starry.teleskope.api.ChannelInfo
import blue.starry.teleskope.api.ProgramInfo
import blue.starry.teleskope.api.ServiceInfo
import blue.starry.teleskope.components.MediaPlayer
import blue.starry.teleskope.components.statusbar.ServiceStatusBarItem
import blue.starry.teleskope.components.statusbar.StatusBar
import blue.starry.teleskope.config.Config
import blue.starry.teleskope.config.ConfigManagerImpl
import blue.starry.teleskope.endpoints.DtvApiClient
import mu.KotlinLogging

@OptIn(ExperimentalComposeUiApi::class)
fun main(args: Array<String>) = application {
    val logger = KotlinLogging.logger("Teleskope.MainWindow")
    val state = rememberWindowState()
    var alwaysOnTop by rememberSaveable { mutableStateOf(false) }
    var config: Config by remember { mutableStateOf(ConfigManagerImpl.load()) }
    var client: DtvApiClient? by remember { mutableStateOf(null as DtvApiClient?) }
    var channels: List<ChannelInfo> by remember { mutableStateOf(emptyList()) }
    var services: List<ServiceInfo> by remember { mutableStateOf(emptyList()) }
    var programs: List<ProgramInfo> by remember { mutableStateOf(emptyList()) }
    var currentChannel: ChannelInfo? by rememberSaveable { mutableStateOf(null) }
    var currentService: ServiceInfo? by rememberSaveable { mutableStateOf(null) }
    var isPerformingTask by remember { mutableStateOf(true) }
    var windowTitle: String by remember { mutableStateOf("Teleskope") }

    // On change config.endpoints
    LaunchedEffect(config.endpoints) {
        logger.debug { "changed: config.endpoints" }
        
        val endpoint = config.endpoints.mirakurun ?: config.endpoints.epgstation ?: return@LaunchedEffect
        client = endpoint.createApiClient()

        channels = client?.getChannels() ?: emptyList()
        services = client?.getServices() ?: emptyList()
        // programs = client?.getPrograms() ?: emptyList()
        isPerformingTask = false
    }
    
    Window(
        state = state,
        alwaysOnTop = alwaysOnTop,
        title = windowTitle
    ) {
        DesktopTheme {
            Column {
                val url = currentService?.let { client?.getServiceStreamUrl(it.id) }
                if (url != null) {
                    MediaPlayer(
                        url, 10,
                        modifier = Modifier.aspectRatio(16 / 9f)
                    )
                } else {
                    Spacer(
                        modifier = Modifier
                            .aspectRatio(16 / 9f)
                            .background(Color.Black)
                    )
                }

                StatusBar {
                    ServiceStatusBarItem(services, currentService, isPerformingTask) { service ->
                        currentService = service
                        windowTitle = "${service.name} - Teleskope"
                    }
                }
            }
        }
    }
}
