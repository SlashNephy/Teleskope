package blue.starry.teleskope.api

import kotlinx.serialization.Serializable

@Serializable
data class ChannelInfo(
    val name: String,
    val type: String,
    val channel: String,
    val services: List<Service>
) {
    @Serializable
    data class Service(
        val id: Long,
        val name: String,
        val serviceId: Int
    )
}
