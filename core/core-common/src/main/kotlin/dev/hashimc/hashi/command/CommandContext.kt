package dev.hashimc.hashi.command

import dev.hashimc.hashi.entity.Player

interface CommandContext {

    val sender: CommandSender
    val player: Player
    val isPlayer: Boolean
    val alias: String
    val input: String

}