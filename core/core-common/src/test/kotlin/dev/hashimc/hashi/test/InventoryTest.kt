package dev.hashimc.hashi.test

import dev.hashimc.hashi.world.inventory.ItemType
import dev.hashimc.hashi.world.inventory.itemStack
import net.kyori.adventure.key.Key

object InventoryTest {

    fun itemStackTest() {
        val appleStack = itemStack(ItemType.of(Key.key("minecraft", "apple")))
    }

}