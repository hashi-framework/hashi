package dev.hashimc.hashi.command

interface CommandMetadata {

    fun alias(alias: String): CommandMetadata

    fun usage(usage: String): CommandMetadata

    fun description(description: String): CommandMetadata

}