package dev.hashimc.hashi.event

import dev.hashimc.hashi.entity.Player
import kotlin.reflect.KProperty

interface PlayerJoinEvent : Event {

    val player: Player

}