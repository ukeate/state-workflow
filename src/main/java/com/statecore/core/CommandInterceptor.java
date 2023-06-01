package com.statecore.core;

public interface CommandInterceptor {
    void setNext(CommandInterceptor interceptor);

    CommandInterceptor getNext();

    Object execute(CommandContext cc, CommandConfig conf, Command cmd);
}
