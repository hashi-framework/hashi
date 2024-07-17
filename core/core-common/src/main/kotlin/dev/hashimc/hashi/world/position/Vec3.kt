package dev.hashimc.hashi.world.position

import dev.hashimc.hashi.extensions.lengthSquared
import dev.hashimc.hashi.extensions.square
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Vec3(
    val x: Double,
    val y: Double,
    val z: Double,
) {

    fun clone(): Vec3 {
        return Vec3(x, y, z)
    }

    fun vectorTo(to: Vec3): Vec3 {
        return Vec3(to.x - this.x, to.y - this.y, to.z - this.z)
    }

    fun normalize(): Vec3 {
        val d0 = sqrt(this.x * this.x + (this.y * this.y) + (this.z * this.z))
        return if (d0 < 1.0E-4) ZERO else Vec3(this.x / d0, this.y / d0, this.z / d0)
    }

    fun dot(another: Vec3): Double {
        return this.x * another.x + (this.y * another.y) + (this.z * another.z)
    }

    fun cross(another: Vec3): Vec3 {
        return Vec3(
            this.y *another.z - this.z * another.y,
            this.z *another.x - this.x * another.z,
            this.x *another.y - this.y * another.x
        )
    }

    operator fun minus(another: Vec3): Vec3 {
        return this.subtract(another.x, another.y, another.z)
    }

    fun subtract(x: Double, y: Double, z: Double): Vec3 {
        return this.add(-x, -y, -z)
    }

    operator fun plus(another: Vec3): Vec3 {
        return this.add(another.x, another.y, another.z)
    }

    fun add(x: Double, y: Double, z: Double): Vec3 {
        return Vec3(this.x + x, this.y + y, this.z + z)
    }

    fun distanceTo(another: Vec3): Double {
        val d0 = another.x - this.x
        val d1 = another.y - this.y
        val d2 = another.z - this.z
        return sqrt(d0 * d0 + d1 * d1 + d2 * d2)
    }

    fun distanceToSqr(another: Vec3): Double {
        val d0 = another.x - this.x
        val d1 = another.y - this.y
        val d2 = another.z - this.z
        return d0 * d0 + d1 * d1 + d2 * d2
    }

    fun distanceToSqr(x: Double, y: Double, z: Double): Double {
        val d0 = x - this.x
        val d1 = y - this.y
        val d2 = z - this.z
        return d0 * d0 + d1 * d1 + d2 * d2
    }

    fun closerThan(another: Vec3, horizontalDistance: Double, verticalDistance: Double): Boolean {
        val d0: Double = another.x - this.x
        val d1: Double = another.y - this.y
        val d2: Double = another.z - this.z
        return lengthSquared(d0, d2) < square(horizontalDistance) && abs(d1) < verticalDistance
    }

    fun scale(xFactor: Double, yFactor: Double, zFactor: Double): Vec3 {
        return Vec3(this.x * xFactor, this.y * yFactor, this.z * zFactor)
    }

    operator fun times(factor: Double): Vec3 {
        return this.scale(factor, factor, factor)
    }

    operator fun unaryMinus(): Vec3 {
        return this.reverse()
    }

    fun reverse(): Vec3 {
        return this * -1.0
    }

    fun length(): Double {
        return sqrt(this.x * this.x + (this.y * this.y) + (this.z * this.z))
    }

    fun lengthSqr(): Double {
        return this.x * this.x + (this.y * this.y) + (this.z * this.z)
    }

    fun horizontalDistance(): Double {
        return sqrt(this.x * this.x + this.z * this.z)
    }

    fun horizontalDistanceSqr(): Double {
        return this.x * this.x + this.z * this.z
    }


    fun xRot(pitch: Float): Vec3 {
        val f: Float = cos(pitch)
        val f1: Float = sin(pitch)
        val d0 = this.x
        val d1 = this.y * f.toDouble() + this.z * f1.toDouble()
        val d2 = this.z * f.toDouble() - this.y * f1.toDouble()
        return Vec3(d0, d1, d2)
    }

    fun yRot(yaw: Float): Vec3 {
        val f: Float = cos(yaw)
        val f1: Float = sin(yaw)
        val d0 = this.x * f.toDouble() + this.z * f1.toDouble()
        val d1 = this.y
        val d2 = this.z * f.toDouble() - this.x * f1.toDouble()
        return Vec3(d0, d1, d2)
    }

    fun zRot(roll: Float): Vec3 {
        val f: Float = cos(roll)
        val f1: Float = sin(roll)
        val d0 = this.x * f.toDouble() + this.y * f1.toDouble()
        val d1 = this.y * f.toDouble() - this.x * f1.toDouble()
        val d2 = this.z
        return Vec3(d0, d1, d2)
    }

    companion object {

        val ZERO = Vec3(0.0, 0.0, 0.0)

    }

}