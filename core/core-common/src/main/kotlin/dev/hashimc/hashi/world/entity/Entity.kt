package dev.hashimc.hashi.world.entity

import dev.hashimc.hashi.world.World
import dev.hashimc.hashi.world.position.Rotation
import dev.hashimc.hashi.world.position.Vec3

interface Entity {

    val world: World
    val location: Vec3
    val rotation: Rotation

    fun teleport(world: World, location: Vec3)

}