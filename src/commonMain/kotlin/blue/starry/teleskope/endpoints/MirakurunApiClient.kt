package blue.starry.teleskope.endpoints

import blue.starry.teleskope.api.ChannelInfo
import blue.starry.teleskope.api.ProgramInfo
import blue.starry.teleskope.api.ServiceInfo
import blue.starry.teleskope.utils.toApiModel
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get

class MirakurunApiClient(private val baseUrl: String) : DtvApiClient {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    override suspend fun getChannels(): List<ChannelInfo> {
        return client.get<List<MirakurunChannel>>("$baseUrl/channels").map { it.toApiModel() }
    }

    override suspend fun getServices(): List<ServiceInfo> {
        return client.get<List<MirakurunService>>("$baseUrl/services").map { it.toApiModel() }
    }
    override suspend fun getService(id: Long): ServiceInfo? {
        TODO("Not yet implemented")
    }
    override fun getServiceStreamUrl(id: Long): String {
        return "$baseUrl/services/$id/stream"
    }

    override suspend fun getPrograms(): List<ProgramInfo> {
        return client.get<List<MirakurunProgram>>("$baseUrl/programs").map { it.toApiModel() }
    }

    override suspend fun getProgram(id: Long): ProgramInfo? {
        TODO("Not yet implemented")
    }

    suspend fun getTuners(): List<MirakurunTuner> = client.get("$baseUrl/tuners")

    override fun close() {
        client.close()
    }
}
