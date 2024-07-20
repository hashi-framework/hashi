package dev.hashimc.hashi.event

import dev.hashimc.hashi.service.Service
import dev.hashimc.hashi.service.service
import kotlin.reflect.KProperty

interface EventManager : Service {

    fun <E: Event> register(eventType: Class<E>, listener: EventListener<E>): EventListener<E>

    companion object : EventManager by service()

}

operator fun <E: Event> EventListener<E>.getValue(thisObj: Any?, property: KProperty<*>): (E) -> Unit = { event -> this.execute(event) }

inline fun <reified E: Event> event(crossinline listener: E.() -> Unit): EventListener<E> {
    return EventManager.register(E::class.java) { event -> listener(event) }
}