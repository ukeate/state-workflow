package com.statecore.core;


public class CommandExecutorImpl implements CommandExecutor {
    private CommandConfig conf;
    private CommandInterceptor first;

    public CommandExecutorImpl(CommandConfig conf, CommandInterceptor first) {
        this.conf = conf;
        this.first = first;
    }


    @Override
    public Object executeWithConfig(CommandContext cc, CommandConfig conf, Command cmd) {
        return this.first.execute(cc, conf, cmd);
    }

    @Override
    public Object execute(CommandContext cc, Command cmd) {
        return this.executeWithConfig(cc, this.conf, cmd);
    }
}
