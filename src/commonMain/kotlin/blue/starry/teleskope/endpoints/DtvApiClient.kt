package blue.starry.teleskope.endpoints

import blue.starry.teleskope.api.ChannelInfo
import blue.starry.teleskope.api.ProgramInfo
import blue.starry.teleskope.api.ServiceInfo
import io.ktor.utils.io.core.Closeable

sealed interface DtvApiClient : Closeable {
    suspend fun getChannels(): List<ChannelInfo>

    suspend fun getServices(): List<ServiceInfo>
    suspend fun getService(id: Long): ServiceInfo?
    fun getServiceStreamUrl(id: Long): String

    suspend fun getPrograms(): List<ProgramInfo>
    suspend fun getProgram(id: Long): ProgramInfo?
}
