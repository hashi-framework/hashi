package dev.hashimc.hashi.world.inventory

import dev.hashimc.hashi.service.service
import net.kyori.adventure.key.Key

interface InventoryManager {

    fun createItemStack(type: ItemType?): ItemStack

    fun getItemType(key: Key): ItemType

    companion object : InventoryManager by service()

}