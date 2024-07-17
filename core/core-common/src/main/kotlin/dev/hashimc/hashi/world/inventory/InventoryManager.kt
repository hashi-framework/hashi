package dev.hashimc.hashi.world.inventory

import dev.hashimc.hashi.service.service

object InventoryManager : IInventoryManager by service() {
}