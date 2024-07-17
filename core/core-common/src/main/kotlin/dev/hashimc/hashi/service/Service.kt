package dev.hashimc.hashi.service

interface Service {

}

inline fun <reified T: Service> service(): T {
    TODO()
}