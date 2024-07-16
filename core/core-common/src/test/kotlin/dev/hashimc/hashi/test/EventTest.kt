package dev.hashimc.hashi.test

import dev.hashimc.hashi.entity.Player
import dev.hashimc.hashi.event.PlayerJoinEvent
import dev.hashimc.hashi.event.event
import dev.hashimc.hashi.event.getValue
import net.kyori.adventure.text.Component

object EventTest {

    val testListener by event<PlayerJoinEvent> {
        this.player.message(Component.text("hello!"))
    }

    fun test() {
        val playerJoinEvent = object : PlayerJoinEvent {
            override val player: Player
                get() = TODO("Not yet implemented")
        }
        this.testListener(playerJoinEvent)
    }

}