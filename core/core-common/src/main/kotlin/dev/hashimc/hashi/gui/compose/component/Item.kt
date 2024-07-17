package dev.hashimc.hashi.gui.compose.component

import androidx.compose.runtime.Composable
import dev.hashimc.hashi.gui.compose.modifier.Modifier
import dev.hashimc.hashi.world.inventory.ItemStack
import net.kyori.adventure.text.Component

@Composable
fun Item(
    icon: ItemStack?,
    // separate item with name & lore to make remember implementable
    name: Component,
    description: List<Component>,
    modifier: Modifier
) {
    TODO()
}