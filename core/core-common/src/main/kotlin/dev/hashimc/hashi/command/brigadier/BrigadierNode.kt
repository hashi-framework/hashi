package dev.hashimc.hashi.command.brigadier

import dev.hashimc.hashi.command.CommandContext
import dev.hashimc.hashi.command.CommandSender
import dev.hashimc.hashi.command.SuggestionContext
import dev.hashimc.hashi.command.argument.ArgumentType

interface BrigadierNode {

    fun literal(name: String, literal: LiteralNode.() -> Unit)

    fun <T> argument(
        name: String,
        argumentType: ArgumentType<T>,
        argument: ArgumentNode.(CommandContext.() -> T) -> Unit,
    )

    fun usage(usage: String)

    fun suggests(suggestion: suspend SuggestionContext.() -> Unit)

    fun requires(requirement: (CommandSender) -> Boolean)

    fun executes(executor: CommandContext.() -> Unit)

}