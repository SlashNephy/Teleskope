package blue.starry.teleskope.api

interface TeleskopePlugin {
    val name: String
    val version: Int
    val description: String
    val compatibility: ApiCompatibleLevel
    
    fun load(api: PluginApi)
    fun unload()
}
