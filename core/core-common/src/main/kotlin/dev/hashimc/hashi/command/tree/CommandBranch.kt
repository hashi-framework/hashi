package dev.hashimc.hashi.command.tree

import dev.hashimc.hashi.command.argument.ArgumentType

interface CommandBranch {

    fun literal(name: String)

    fun required(name: String, argumentType: ArgumentType<*>)

    fun optional(name: String, argumentType: ArgumentType<*>)

    fun executes(executor: TreeExecutor)

}