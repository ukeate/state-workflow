package com.statecore.core;

public interface Command {
    Object execute(CommandContext cc);
}
