package dev.hashimc.hashi.plugin

import java.nio.file.Path

abstract class Plugin {

    abstract fun onLoad()

    abstract fun onEnable()

    abstract fun onDisable()

    fun getConfigDir(): Path {
        TODO()
    }

}