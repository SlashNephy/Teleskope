package blue.starry.teleskope.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val endpoints: Endpoints = Endpoints()
) {
    @Serializable
    data class Endpoints(
        val mirakurun: MirakurunEndpointConfig? = null,
        val epgstation: EpgStationEndpointConfig? = null
    )
}
