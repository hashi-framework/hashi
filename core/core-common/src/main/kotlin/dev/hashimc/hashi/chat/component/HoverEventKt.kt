package ink.pmc.advkt.component

import dev.hashimc.hashi.chat.component.HoverEventKt
import dev.hashimc.hashi.chat.component.HoverEventWithoutKt
import dev.hashimc.hashi.chat.component.RootComponentKt
import net.kyori.adventure.text.event.HoverEvent

fun hoverEvent(): HoverEventWithoutKt {
    return HoverEventWithoutKt()
}

inline fun showText(text: RootComponentKt.() -> Unit): HoverEventKt {
    return HoverEventKt(HoverEvent.showText(RootComponentKt().apply(text).build()))
}

fun HoverEvent<*>.kt(): HoverEventKt {
    return HoverEventKt(this)
}