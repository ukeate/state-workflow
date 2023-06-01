package com.statecore.core;

public class CommandInvoker implements CommandInterceptor {
    @Override
    public void setNext(CommandInterceptor interceptor) {
    }

    @Override
    public CommandInterceptor getNext() {
        return null;
    }

    @Override
    public Object execute(CommandContext cc, CommandConfig conf, Command cmd) {
        return cmd.execute(cc);
    }
}
