package dev.hashimc.hashi.command.simple

import dev.hashimc.hashi.command.CommandContext

fun interface SimpleCommandExecutor {

    suspend fun tabComplete(context: CommandContext, args: Array<String>): List<String> {
        return listOf()
    }

    fun execute(context: CommandContext, args: Array<String>)

}