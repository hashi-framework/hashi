package dev.hashimc.hashi.world.entity

import dev.hashimc.hashi.world.inventory.Equipment

interface LivingEntity : Entity {

    val equipment: Equipment

}