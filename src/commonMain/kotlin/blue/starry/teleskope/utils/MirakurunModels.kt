package blue.starry.teleskope.utils

import blue.starry.teleskope.api.ChannelInfo
import blue.starry.teleskope.api.ProgramInfo
import blue.starry.teleskope.api.ServiceInfo
import blue.starry.teleskope.endpoints.MirakurunChannel
import blue.starry.teleskope.endpoints.MirakurunProgram
import blue.starry.teleskope.endpoints.MirakurunService

fun MirakurunChannel.toApiModel(): ChannelInfo {
    return ChannelInfo(
        name = name,
        type = type,
        channel = channel,
        services = services.map {
            ChannelInfo.Service(
                id = it.id,
                name = it.name,
                serviceId = it.serviceId
            )
        }
    )
}

fun MirakurunService.toApiModel(): ServiceInfo {
    return ServiceInfo(
        id = id,
        type = channel.type,
        channel = channel.channel,
        name = name,
        serviceId = serviceId,
        networkId = networkId,
        remoteControlKeyId = remoteControlKeyId,
        logoId = logoId,
        hasLogoData = hasLogoData
    )
}

fun MirakurunProgram.toApiModel(): ProgramInfo {
    return ProgramInfo(
        id = id,
        eventId = eventId,
        serviceId = serviceId,
        transportStreamId = transportStreamId,
        networkId = networkId,
        startAt = startAt,
        duration = duration,
        isFree = isFree,
        name = name,
        description = description,
        extended = extended,
        video = video?.toApiModel(),
        audio = audio?.toApiModel(),
        genres = genres.map { it.toApiModel() }
    )
}

fun MirakurunProgram.Video.toApiModel(): ProgramInfo.Video {
    return ProgramInfo.Video(
        type = type,
        resolution = resolution,
        streamContent = streamContent,
        componentType = componentType
    )
}

fun MirakurunProgram.Audio.toApiModel(): ProgramInfo.Audio {
    return ProgramInfo.Audio(
        samplingRate = samplingRate,
        componentType = componentType
    )
}

fun MirakurunProgram.Genre.toApiModel(): ProgramInfo.Genre {
    return ProgramInfo.Genre(
        lv1 = lv1,
        lv2 = lv2,
        un1 = un1,
        un2 = un2
    )
}
