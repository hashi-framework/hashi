package dev.hashimc.hashi.event

import dev.hashimc.hashi.world.entity.Player
import kotlin.reflect.KProperty

interface PlayerJoinEvent : Event {

    val player: Player

}