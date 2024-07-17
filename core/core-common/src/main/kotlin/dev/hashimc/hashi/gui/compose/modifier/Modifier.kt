@file:Suppress("UNCHECKED_CAST")

package dev.hashimc.hashi.gui.compose.modifier

interface Modifier {

    fun <R> foldIn(initial: R, operation: (R, Element<*>) -> R): R

    fun <R> foldOut(initial: R, operation: (Element<*>, R) -> R): R

    fun any(predicate: (Element<*>) -> Boolean): Boolean

    fun all(predicate: (Element<*>) -> Boolean): Boolean

    infix fun then(other: Modifier): Modifier =
        if (other === Modifier) this else CombinedModifier(this, other)

    interface Element<Self : Element<Self>> : Modifier {
        override fun <R> foldIn(initial: R, operation: (R, Element<*>) -> R): R =
            operation(initial, this)

        override fun <R> foldOut(initial: R, operation: (Element<*>, R) -> R): R =
            operation(this, initial)

        override fun any(predicate: (Element<*>) -> Boolean): Boolean = predicate(this)

        override fun all(predicate: (Element<*>) -> Boolean): Boolean = predicate(this)

        fun mergeWith(other: Self): Self

        fun unsafeMergeWith(other: Element<*>) = mergeWith(other as Self)
    }

    companion object : Modifier {

        override fun <R> foldIn(initial: R, operation: (R, Element<*>) -> R): R = initial

        override fun <R> foldOut(initial: R, operation: (Element<*>, R) -> R): R = initial

        override fun any(predicate: (Element<*>) -> Boolean): Boolean = false

        override fun all(predicate: (Element<*>) -> Boolean): Boolean = true

        override infix fun then(other: Modifier): Modifier = other

        override fun toString() = "Modifier"
    }

}

class CombinedModifier(
    private val outer: Modifier,
    private val inner: Modifier
) : Modifier {
    override fun <R> foldIn(initial: R, operation: (R, Modifier.Element<*>) -> R): R =
        inner.foldIn(outer.foldIn(initial, operation), operation)

    override fun <R> foldOut(initial: R, operation: (Modifier.Element<*>, R) -> R): R =
        outer.foldOut(inner.foldOut(initial, operation), operation)

    override fun any(predicate: (Modifier.Element<*>) -> Boolean): Boolean =
        outer.any(predicate) || inner.any(predicate)

    override fun all(predicate: (Modifier.Element<*>) -> Boolean): Boolean =
        outer.all(predicate) && inner.all(predicate)

    override fun equals(other: Any?): Boolean =
        other is CombinedModifier && outer == other.outer && inner == other.inner

    override fun hashCode(): Int = outer.hashCode() + 31 * inner.hashCode()

    override fun toString() = "[" + foldIn("") { acc, element ->
        if (acc.isEmpty()) element.toString() else "$acc, $element"
    } + "]"

}
