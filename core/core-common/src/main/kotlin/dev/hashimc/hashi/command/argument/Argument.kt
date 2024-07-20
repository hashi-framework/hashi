package dev.hashimc.hashi.command.argument

import dev.hashimc.hashi.service.Service
import dev.hashimc.hashi.service.service
import java.util.*


interface Argument : Service {

    fun string(): ArgumentType<String>

    fun word(): ArgumentType<String>

    fun greedy(): ArgumentType<String>

    fun int(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): ArgumentType<Int>

    fun long(min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE): ArgumentType<Long>

    fun float(min: Float = Float.MIN_VALUE, max: Float = Float.MAX_VALUE): ArgumentType<Float>

    fun double(min: Double = Double.MIN_VALUE, max: Double = Double.MAX_VALUE): ArgumentType<Double>

    fun boolean(): ArgumentType<Boolean>

    fun uuid(): ArgumentType<UUID>

    companion object : Argument by service()

}