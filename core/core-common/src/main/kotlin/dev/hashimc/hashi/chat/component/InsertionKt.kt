package dev.hashimc.hashi.chat.component

fun insertion(): InsertionWithoutKt {
    return InsertionWithoutKt()
}

fun insertion(value: String): InsertionKt {
    return InsertionKt(value)
}