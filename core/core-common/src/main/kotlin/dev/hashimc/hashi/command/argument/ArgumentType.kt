package dev.hashimc.hashi.command.argument

import dev.hashimc.hashi.command.CommandContext

interface ArgumentType<T> {

    fun parse(name: String, context: CommandContext): T

}