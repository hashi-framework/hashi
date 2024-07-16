package dev.hashimc.hashi.test

import dev.hashimc.hashi.chat.component.RootComponentKt
import dev.hashimc.hashi.chat.component.behaviour
import dev.hashimc.hashi.chat.component.plain
import net.kyori.adventure.text.Component

object ChatTest {

    fun itemBuilderExample() {

        fun itemLoreBuilder(func: RootComponentKt.() -> Unit): List<Component> {
            val component = RootComponentKt()

            val lore = mutableListOf<Component>()

            component.behaviour {
                newline { root, components ->
                    lore.add(root.build())
                    components.clear()
                }
            }

            component.apply(func)

            if (component.size() > 0) {
                lore.add(component.build()) // ensure all the text appended
            }

            return lore.toList()
        }


        val lore = itemLoreBuilder {
            text("fuck")
            newline()
            text("should be next line")
        }

        for (line in lore) {
            println(line.plain())
        }
    }

}