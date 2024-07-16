package dev.hashimc.hashi.event

fun interface EventListener<E: Event> {

    fun execute(e: E)

}