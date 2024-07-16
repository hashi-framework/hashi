package dev.hashimc.hashi.command.tree

import dev.hashimc.hashi.command.CommandContext

fun interface TreeExecutor {

    fun execute(context: CommandContext, args: Array<Any>)

}