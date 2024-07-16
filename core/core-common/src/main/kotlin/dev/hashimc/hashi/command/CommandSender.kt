package dev.hashimc.hashi.command

import net.kyori.adventure.text.Component

interface CommandSender {

    fun message(message: String)

    fun message(message: Component)

}