package dev.hashimc.hashi.world.inventory

interface ItemStack {
}

fun itemStack(type: ItemType?): ItemStack {
    return InventoryManager.createItemStack(type)
}