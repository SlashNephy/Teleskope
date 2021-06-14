package blue.starry.teleskope.endpoints

import kotlinx.serialization.Serializable

@Serializable
data class MirakurunChannel(
    val type: String,
    val channel: String,
    val name: String,
    val services: List<Service>
) {
    @Serializable
    data class Service(
        val id: Long,
        val serviceId: Int,
        // Mirakurun not supported, only mirakc supports transportStreamId.
        val transportStreamId: Int? = null,
        val networkId: Int,
        val name: String
    )
}

@Serializable
data class MirakurunService(
    val id: Long,
    val serviceId: Int,
    // Mirakurun not supported, only mirakc supports transportStreamId.
    val transportStreamId: Int? = null,
    val networkId: Int,
    val type: Int,
    val logoId: Int,
    val remoteControlKeyId: Int,
    val name: String,
    val channel: Channel,
    val hasLogoData: Boolean
) {
    @Serializable
    data class Channel(
        val type: String,
        val channel: String
    )
}

@Serializable
data class MirakurunProgram(
    val id: Long,
    val eventId: Int,
    val serviceId: Int,
    // Mirakurun not supported, only mirakc supports transportStreamId.
    val transportStreamId: Int? = null,
    val networkId: Int,
    val startAt: Long,
    val duration: Int,
    val isFree: Boolean,
    val name: String? = null,
    val description: String? = null,
    val extended: String? = null,
    val video: Video? = null,
    val audio: Audio? = null,
    val genres: List<Genre> = emptyList()
) {
    @Serializable
    data class Video(
        val type: String?,
        val resolution: String?,
        val streamContent: Int,
        val componentType: Int
    )

    @Serializable
    data class Audio(
        val samplingRate: Int,
        val componentType: Int
    )

    @Serializable
    data class Genre(
        val lv1: Int,
        val lv2: Int,
        val un1: Int,
        val un2: Int
    )
}

@Serializable
data class MirakurunTuner(
    val index: Int,
    val name: String,
    val types: List<String>,
    val command: String?,
    val pid: Int?,
    val users: List<User>,
    val isAvailable: Boolean,
    val isRemote: Boolean,
    val isFree: Boolean,
    val isUsing: Boolean,
    val isFault: Boolean
) {
    @Serializable
    data class User(
        val id: String,
        val agent: String? = null,
        val priority: Int
    )
}
