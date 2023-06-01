package com.statecore.core;

public interface CommandExecutor {
    Object execute(CommandContext cc, Command cmd);
    Object executeWithConfig(CommandContext cc,CommandConfig conf, Command cmd);
}
