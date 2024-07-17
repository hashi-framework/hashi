package dev.hashimc.hashi.world.inventory

interface Inventory {

    val size: Int

    operator fun get(index: Int): ItemStack

    operator fun set(index: Int, itemStack: ItemStack?)

}