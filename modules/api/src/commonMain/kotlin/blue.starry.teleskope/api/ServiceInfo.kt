package blue.starry.teleskope.api

import kotlinx.serialization.Serializable

@Serializable
data class ServiceInfo(
    val id: Long,
    val type: String,
    val channel: String,
    val name: String,
    val serviceId: Int,
    val networkId: Int,
    val remoteControlKeyId: Int,
    val logoId: Int,
    val hasLogoData: Boolean
)
