package dev.hashimc.hashi.command.cloud

import dev.hashimc.hashi.command.CommandContext
import dev.hashimc.hashi.command.argument.ArgumentType

interface CloudNode {

    operator fun String.invoke(node: CloudNode.() -> Unit)

    fun required(name: String, argumentType: ArgumentType<*>)

    fun optional(name: String, argumentType: ArgumentType<*>)

    fun executes(executor: CommandContext.() -> Unit)

    operator fun <T> CommandContext.get(name: String): T

    fun <T> CommandContext.getOrDefault(name: String, default: T): T

}