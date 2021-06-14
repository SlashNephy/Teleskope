package blue.starry.teleskope.api

import kotlinx.serialization.Serializable

@Serializable
data class ProgramInfo(
    val id: Long,
    val eventId: Int,
    val serviceId: Int,
    val transportStreamId: Int?,
    val networkId: Int,
    val startAt: Long,
    val duration: Int,
    val isFree: Boolean,
    val name: String?,
    val description: String?,
    val extended: String?,
    val video: Video?,
    val audio: Audio?,
    val genres: List<Genre>
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
