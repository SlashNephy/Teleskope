package blue.starry.teleskope.api

interface PluginApi {
    fun getCurrentChannel(): ChannelInfo?
    fun getCurrentService(): ServiceInfo?
    fun getChannel(channel: String): ChannelInfo?
    fun getService(id: Long): ServiceInfo?
    fun getService(type: String, serviceId: Int): ServiceInfo?
}
