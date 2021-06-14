package blue.starry.teleskope.config

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.readText
import kotlin.io.path.writeText

internal actual object ConfigManagerImpl : ConfigManager {
    private val configPath
        get() = Paths.get("config.yml")
    private val serializer
        get() = Yaml(
            configuration = YamlConfiguration(
                // ignore unknown key error
                strictMode = false
            )
        )

    override fun load(): Config {
        if (Files.notExists(configPath)) {
            return Config()
        }

        val yml = configPath.readText()
        return serializer.decodeFromString(yml)
    }

    override fun save(config: Config) {
        val yml = Yaml.default.encodeToString(config)
        configPath.writeText(yml)
    }
}
