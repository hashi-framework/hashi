package dev.hashimc.hashi.event

import dev.hashimc.hashi.service.Service
import kotlin.reflect.KProperty

interface IEventManager: Service {

    fun <E: Event> register(eventType: Class<E>, listener: EventListener<E>): EventListener<E>

}

operator fun <E: Event> EventListener<E>.getValue(thisObj: Any?, property: KProperty<*>): (E) -> Unit = { event -> this.execute(event) }

inline fun <reified E: Event> event(crossinline listener: E.() -> Unit): EventListener<E> {
    return EventManager.register(E::class.java) { event -> listener(event) }
}