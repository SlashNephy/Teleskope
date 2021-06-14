package blue.starry.teleskope.config

import blue.starry.teleskope.endpoints.DtvApiClient
import blue.starry.teleskope.endpoints.EpgStationApiClient
import blue.starry.teleskope.endpoints.MirakurunApiClient
import kotlinx.serialization.Serializable

sealed interface EndpointConfig<T : DtvApiClient> {
    val url: String
    fun createApiClient(): T
}

@Serializable
data class MirakurunEndpointConfig(
    override val url: String,
    val headers: Map<String, String> = emptyMap()
) : EndpointConfig<MirakurunApiClient> {
    override fun createApiClient(): MirakurunApiClient {
        return MirakurunApiClient(url)
    }
}

@Serializable
data class EpgStationEndpointConfig(
    override val url: String,
    val headers: Map<String, String> = emptyMap()
) : EndpointConfig<EpgStationApiClient> {
    override fun createApiClient(): EpgStationApiClient {
        return EpgStationApiClient(url)
    }
}
