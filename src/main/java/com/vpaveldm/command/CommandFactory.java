package com.vpaveldm.command;

public class CommandFactory {

    private static final String CHECK = "CHECK";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE";
    private static final String ADD = "ADD";
    private static final String ADDED = "ADDED";
    private static final String DELETED = "DELETED";
    private static final String UPDATED = "UPDATED";
    private static final String CHECKED = "CHECKED";

    public boolean isHandleCommand(String command) {
        switch (command) {
            case DELETED:
            case CHECKED:
            case UPDATED:
            case ADDED: {
                return true;
            }
            default:
                return false;
        }
    }

    public ICommand defineCommand(String command) {
        switch (command) {
            case CHECKED:
            case CHECK: {
                return new CheckCommand();
            }
            case UPDATED:
            case UPDATE: {
                return new UpdateCommand();
            }
            case ADDED:
            case ADD: {
                return new AddCommand();
            }
            case DELETED:
            case DELETE: {
                return new DeleteCommand();
            }
            default:
                throw new UnsupportedOperationException("the command " + command + " isn't supported");
        }
    }

}
