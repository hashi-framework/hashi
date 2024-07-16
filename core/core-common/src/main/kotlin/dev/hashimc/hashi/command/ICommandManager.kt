package dev.hashimc.hashi.command

import dev.hashimc.hashi.command.brigadier.BrigadierCommand
import dev.hashimc.hashi.command.brigadier.LiteralNode
import dev.hashimc.hashi.command.cloud.CloudCommand
import dev.hashimc.hashi.command.cloud.CloudNode
import dev.hashimc.hashi.command.simple.SimpleCommand
import dev.hashimc.hashi.command.simple.SimpleCommandExecutor
import dev.hashimc.hashi.command.tree.CommandTree
import dev.hashimc.hashi.command.tree.TreeCommand
import dev.hashimc.hashi.service.Service

interface ICommandManager : Service {

    fun createMetadata(): CommandMetadata

    fun createSimple(name: String, executor: SimpleCommandExecutor): SimpleCommand

    fun createTree(name: String, builder: CommandTree.() -> Unit): TreeCommand

    fun createCloud(name: String, builder: CloudNode.() -> Unit): CloudCommand

    fun createBrigadier(name: String, builder: LiteralNode.() -> Unit): BrigadierCommand

    fun registerCommand(metadata: CommandMetadata, command: Command)

}