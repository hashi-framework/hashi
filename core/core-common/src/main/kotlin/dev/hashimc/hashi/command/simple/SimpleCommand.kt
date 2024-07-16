package dev.hashimc.hashi.command.simple

import dev.hashimc.hashi.command.Command

interface SimpleCommand : Command {

    val executor: SimpleCommandExecutor

}