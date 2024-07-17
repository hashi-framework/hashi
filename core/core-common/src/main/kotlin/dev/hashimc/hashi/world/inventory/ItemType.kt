package dev.hashimc.hashi.world.inventory

import net.kyori.adventure.key.Key

interface ItemType {

    companion object {

        fun of(key: Key): ItemType {
            return InventoryManager.getItemType(key)
        }

    }

}