package dev.hashimc.hashi.command

import net.kyori.adventure.text.Component

interface SuggestionContext : CommandContext {

    val start: Int

    val remaining: String

    fun suggest(text: String, tooltip: Component? = null)

    fun suggest(text: Int, tooltip: Component? = null)

}