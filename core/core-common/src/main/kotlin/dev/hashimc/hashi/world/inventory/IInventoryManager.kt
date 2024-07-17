package dev.hashimc.hashi.world.inventory

import dev.hashimc.hashi.service.Service
import net.kyori.adventure.key.Key

interface IInventoryManager : Service {

    fun createItemStack(type: ItemType?): ItemStack

    fun getItemType(key: Key): ItemType

}