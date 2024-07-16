package dev.hashimc.hashi.command.tree

interface CommandTree {

    fun branch(branch: CommandBranch.() -> Unit)

}