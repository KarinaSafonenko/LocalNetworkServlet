package com.vpaveldm.command;

public class CommandFactory {

    public static final String CHANGE = "CHANGE";
    public static final String CHECK = "CHECK";

    public ICommand defineCommand(String command) {
        switch (command){
            case CHANGE:{
                return new ChangeCommand();
            }
            case CHECK:{
                return new CheckCommand();
            }
            default:
                throw new UnsupportedOperationException("the command " + command + " isn't supported");
        }
    }

}
