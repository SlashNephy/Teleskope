package blue.starry.teleskope.endpoints

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val code: Int,
    val message: String,
    val errors: String
)

@Serializable
data class Channel(
    val id: Long,
    val serviceId: Int,
    val networkId: Int,
    val name: String,
    val remoteControlKeyId: Int,
    val hasLogoData: Boolean,
    val channelType: String,
    val channel: String,
    val type: Int,
)
