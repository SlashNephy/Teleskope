package blue.starry.teleskope.config

interface ConfigManager {
    fun load(): Config
    fun save(config: Config)
}

internal expect object ConfigManagerImpl: ConfigManager
