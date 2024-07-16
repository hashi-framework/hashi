package dev.hashimc.hashi.test

import dev.hashimc.hashi.command.CommandManager
import dev.hashimc.hashi.command.argument.Argument
import net.kyori.adventure.text.Component

object CommandTest {

    fun tree() {
        CommandManager.createTree("tree") {
            branch {
                literal("sub")
                required("name", Argument.string())

                // /tree sub <name>
                executes { context, args ->
                    val name = args[1] as String
                }
            }
            branch {
                literal("sub")
                literal("test")

                // /tree sub test
                executes { context, args ->
                }
            }
        }
    }

    fun cloud() {
        CommandManager.createCloud("cloud") {
            "sub" {
                required("name", Argument.string())
                optional("age", Argument.int(min = 0))

                executes {
                    val name: String = this["name"]
                    val age: Int = this.getOrDefault("age", 0)
                }
            }
        }
    }

    fun brigadier() {
        CommandManager.createBrigadier("brigadier") {
            literal("sub") {
                argument("name", Argument.string()) { nameFunc ->

                    suggests {
                        suggest("DeeChael", Component.text("Designer of the command framework"))
                        suggest("nostalfinals", Component.text("Founder of Hashi framework"))
                    }

                    executes {
                        val name = nameFunc()
                    }
                }
            }

        }
    }

}