package dev.hashimc.hashi.chat.component

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.ansi.ANSIComponentSerializer
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.ansi.ColorLevel

interface ComponentKt {

    fun build(): Component

}

class TextComponentKt(internal var component: Component) : ComponentKt {

    override fun build(): Component {
        return this.component
    }

}

class RootComponentKt : ComponentKt {

    internal var miniMessage: MiniMessage = MiniMessage.miniMessage()
    internal var behaviour: RootBehaviour = RootBehaviour.Builder().build()
    internal var defaults: RootDefaults? = null
    internal var replaces: RootReplaces? = null
    internal var join: JoinConfiguration? = null
    internal val components: MutableList<ComponentKt> = mutableListOf()

    infix fun TextComponentKt.with(styleKt: StyleKt): TextComponentKt {
        this.component = this.component.style(styleKt.with(this.component.style()))
        return this
    }

    infix fun TextComponentKt.with(color: GradientColor): TextComponentKt {
        this.component = this@RootComponentKt.miniMessage.deserialize(
            "<gradient${color.buildString()}>${this@RootComponentKt.miniMessage.serialize(this.component)}</gradient>"
        )
        return this
    }

    infix fun TextComponentKt.without(styleKt: StyleKt): TextComponentKt {
        this.component = this.component.style(styleKt.without(this.component.style()))
        return this
    }

    fun raw(text: Component): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(text))
    }

    fun text(text: String): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.text(text)))
    }

    fun text(text: Char): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.text(text)))
    }

    fun text(text: Boolean): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.text(text)))
    }

    fun text(text: Int): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.text(text)))
    }

    fun text(text: Long): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.text(text)))
    }

    fun text(text: Float): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.text(text)))
    }

    fun text(text: Double): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.text(text)))
    }

    fun translatable(key: String): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.translatable(key)))
    }

    fun keybind(keybind: String): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(Component.keybind(keybind)))
    }

    fun miniMessage(rawText: String): TextComponentKt {
        return this.behaviour.text(this, this.components, TextComponentKt(this.miniMessage.deserialize(rawText)))
    }

    fun newline(): TextComponentKt {
        return this.behaviour.newline(this, this.components)
    }

    fun empty(): TextComponentKt {
        return this.behaviour.empty(this, this.components)
    }

    fun space(): TextComponentKt {
        return this.behaviour.space(this, this.components)
    }

    fun size(): Int {
        return this.components.size
    }

    override fun build(): Component {
        if (join == null) {
            var root: Component = Component.empty()
            if (this.defaults != null) {
                root = root.style(this.defaults!!.style)
                if (this.defaults!!.hoverEvent != null) {
                    root = root.hoverEvent(this.defaults!!.hoverEvent)
                }
                if (this.defaults!!.clickEvent != null) {
                    root = root.clickEvent(this.defaults!!.clickEvent)
                }
            }
            for (component in this.components) {
                root = root.append(component.build())
            }
            return applyReplacement(root)
        } else {
            return Component.join(this.join!!, this.components.map { applyReplacement(it.build()) })
        }
    }

    private fun applyReplacement(component: Component): Component {
        var variable = component
        if (this.replaces != null) {
            var replacer = this.replaces
            while (replacer?.parent != null && !replacer.overriden) {
                for (replacementConfig in replacer.replaces) {
                    variable = variable.replaceText(replacementConfig)
                }
                replacer = replacer.parent
            }
        }
        return variable
    }

}

class RootDefaults {

    internal var style: Style = Style.empty()
    internal var hoverEvent: HoverEvent<*>? = null
    internal var clickEvent: ClickEvent? = null

    fun with(styleKt: StyleKt) {
        this.style = styleKt.with(this.style)
    }

    fun without(styleKt: StyleKt) {
        this.style = styleKt.without(this.style)
    }

}

class RootReplaces(
    internal val parent: RootReplaces?,
) {

    internal var overriden: Boolean = false
    internal val replaces: MutableList<TextReplacementConfig> = mutableListOf()

}

class RootBehaviour(
    private val textFunc: (root: RootComponentKt, MutableList<ComponentKt>, TextComponentKt) -> Unit,
    private val newlineFunc: (root: RootComponentKt, MutableList<ComponentKt>) -> Unit,
    private val emptyFunc: (root: RootComponentKt, MutableList<ComponentKt>) -> Unit,
    private val spaceFunc: (root: RootComponentKt, MutableList<ComponentKt>) -> Unit,
) {

    fun text(
        root: RootComponentKt,
        appendedList: MutableList<ComponentKt>,
        readyToAppend: TextComponentKt,
    ): TextComponentKt {
        this.textFunc(root, appendedList, readyToAppend)
        return readyToAppend
    }

    fun newline(root: RootComponentKt, appendedList: MutableList<ComponentKt>): TextComponentKt {
        this.newlineFunc(root, appendedList)
        return TextComponentKt(Component.newline())
    }

    fun empty(root: RootComponentKt, appendedList: MutableList<ComponentKt>): TextComponentKt {
        this.emptyFunc(root, appendedList)
        return TextComponentKt(Component.empty())
    }

    fun space(root: RootComponentKt, appendedList: MutableList<ComponentKt>): TextComponentKt {
        this.spaceFunc(root, appendedList)
        return TextComponentKt(Component.space())
    }

    class Builder {

        private var textFunc =
            fun(root: RootComponentKt, appendedList: MutableList<ComponentKt>, readyToAppend: TextComponentKt) {
                appendedList.add(readyToAppend)
            }

        private var newlineFunc = fun(root: RootComponentKt, appendedList: MutableList<ComponentKt>) {
            val component = TextComponentKt(Component.newline())
            appendedList.add(component)
        }

        private var emptyFunc = fun(root: RootComponentKt, appendedList: MutableList<ComponentKt>) {
            val component = TextComponentKt(Component.empty())
            appendedList.add(component)
        }

        private var spaceFunc = fun(root: RootComponentKt, appendedList: MutableList<ComponentKt>) {
            val component = TextComponentKt(Component.space())
            appendedList.add(component)
        }

        fun text(func: (root: RootComponentKt, appendedList: MutableList<ComponentKt>, readyToAppend: TextComponentKt) -> Unit) {
            this.textFunc = func
        }

        fun newline(func: (root: RootComponentKt, appendedList: MutableList<ComponentKt>) -> Unit) {
            this.newlineFunc = func
        }

        fun empty(func: (root: RootComponentKt, appendedList: MutableList<ComponentKt>) -> Unit) {
            this.emptyFunc = func
        }

        fun space(func: (root: RootComponentKt, appendedList: MutableList<ComponentKt>) -> Unit) {
            this.spaceFunc = func
        }

        internal fun build(): RootBehaviour {
            return RootBehaviour(
                this.textFunc,
                this.newlineFunc,
                this.emptyFunc,
                this.spaceFunc
            )
        }

    }

}

inline fun component(content: RootComponentKt.() -> Unit): Component {
    return RootComponentKt().apply(content).build()
}

fun RootComponentKt.behaviour(content: RootBehaviour.Builder.() -> Unit) {
    this.behaviour = RootBehaviour.Builder().apply(content).build()
}

fun RootComponentKt.defaults(content: RootDefaults.() -> Unit) {
    this.defaults = RootDefaults().apply(content)
}

fun RootComponentKt.provide(miniMessage: MiniMessage) {
    this.miniMessage = miniMessage
}

fun RootComponentKt.provide(builder: MiniMessage.Builder.() -> Unit) {
    this.miniMessage = MiniMessage.builder().apply(builder).build()
}

fun RootComponentKt.component(content: RootComponentKt.() -> Unit) {
    val component = RootComponentKt()
    component.miniMessage = this.miniMessage
    component.replaces = this.replaces
    this.components.add(component.apply(content))
}

fun RootComponentKt.join(content: JoinConfiguration.Builder.() -> Unit) {
    this.join = JoinConfiguration.builder().apply(content).build()
}

fun RootComponentKt.replacements(content: RootReplaces.() -> Unit) {
    if (this.replaces != null) {
        this.replaces!!.apply(content)
    } else {
        this.replaces = RootReplaces(null).apply(content)
    }
}

fun RootReplaces.replacement(content: TextReplacementConfig.Builder.() -> Unit) {
    this.replaces.add(TextReplacementConfig.builder().apply(content).build())
}

fun RootReplaces.override() {
    this.overriden = true
}

fun Component.json(): String {
    return GsonComponentSerializer.gson().serialize(this)
}

fun Component.legacy(): String {
    return LegacyComponentSerializer.legacySection().serialize(this)
}

fun Component.plain(): String {
    return PlainTextComponentSerializer.plainText().serialize(this)
}

fun Component.ansi(): String {
    return ANSIComponentSerializer.builder().colorLevel(ColorLevel.TRUE_COLOR).build().serialize(this)
}

fun Component.replace(string: String, text: String, literal: Boolean = false): Component {
    return this.replace(string, Component.text(text), literal)
}

fun Component.replace(string: String, component: Component, literal: Boolean = false): Component {
    val replaceConfig = TextReplacementConfig.builder()
        .apply {
            if (literal)
                this.matchLiteral(string)
            else
                this.match(string)
        }
        .replacement(component)
        .build()

    return this.replaceText(replaceConfig)
}

fun Component.replace(string: String, literal: Boolean = false, replacement: (Component) -> Component): Component {
    val replaceConfig = TextReplacementConfig.builder()
        .apply {
            if (literal)
                this.matchLiteral(string)
            else
                this.match(string)
        }
        .replacement { builder ->
            return@replacement replacement(builder.build())
        }
        .build()

    return this.replaceText(replaceConfig)
}

fun Component.replaceColor(targetColor: TextColor, newColor: TextColor): Component {
    val updatedComponent = if (this.color() == targetColor) {
        this.color(newColor)
    } else {
        this
    }

    return updatedComponent.children().fold(updatedComponent.children(emptyList())) { component, child ->
        component.append(child.replaceColor(targetColor, newColor))
    }
}

fun TextReplacementConfig.Builder.replace(content: RootComponentKt.(original: Component) -> Unit) {
    this.replacement { builder ->
        return@replacement RootComponentKt().apply {
            this.content(builder.build())
        }.build()
    }
}