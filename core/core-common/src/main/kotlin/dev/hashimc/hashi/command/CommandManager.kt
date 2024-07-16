package dev.hashimc.hashi.command

import dev.hashimc.hashi.service.service

object CommandManager : ICommandManager by service()