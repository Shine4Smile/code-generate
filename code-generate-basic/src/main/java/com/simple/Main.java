package com.simple;

import com.simple.cli.CommandExecutor;

public class Main {

    public static void main(String[] args) {
        args = new String[]{"generate", "-l", "-u", "-o"};
//        args = new String[]{"conf"};
//        args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
