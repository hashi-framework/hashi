package dev.hashimc.hashi.service

interface Service {

}

fun <T: Service> service(): T {
    TODO()
}