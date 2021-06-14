package blue.starry.teleskope.plugins

interface PluginManager {
    fun load()
    fun unload()
}

internal expect class PluginManagerImpl : PluginManager
