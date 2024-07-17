package dev.hashimc.hashi.world.entity

import dev.hashimc.hashi.command.CommandSender
import dev.hashimc.hashi.world.inventory.PlayerInventory

interface Player : Entity, CommandSender {

    val inventory: PlayerInventory

}