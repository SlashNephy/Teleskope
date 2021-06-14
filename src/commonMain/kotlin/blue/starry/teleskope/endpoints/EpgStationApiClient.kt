package blue.starry.teleskope.endpoints

import blue.starry.teleskope.api.ChannelInfo
import blue.starry.teleskope.api.ProgramInfo
import blue.starry.teleskope.api.ServiceInfo
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get

class EpgStationApiClient(private val baseUrl: String): DtvApiClient {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    override suspend fun getChannels(): List<ChannelInfo> {
        TODO("Not yet implemented")
    }
    // suspend fun getChannels(): List<Channel> = client.get("$baseUrl/channels")
    suspend fun getChannelLogo(id: Long): ByteArray = client.get("$baseUrl/channels/$id/logo")

    override suspend fun getServices(): List<ServiceInfo> {
        TODO("Not yet implemented")
    }
    override suspend fun getService(id: Long): ServiceInfo? {
        TODO("Not yet implemented")
    }
    override fun getServiceStreamUrl(id: Long): String {
        TODO("Not yet implemented")
    }

    override suspend fun getPrograms(): List<ProgramInfo> {
        TODO("Not yet implemented")
    }
    override suspend fun getProgram(id: Long): ProgramInfo? {
        TODO("Not yet implemented")
    }
    
    override fun close() {
        client.close()
    }
}
