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
    private static final String GOOGLE = "GOOGLE";
    private static final String GOOGLE_FINISHED = "GOOGLE_FINISHED";

    public boolean isHandleCommand(String command) {
        switch (command) {
            case GOOGLE_FINISHED:
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
            case GOOGLE:
            case GOOGLE_FINISHED: {
                return new GoogleImplCommand();
            }
            default:
                throw new UnsupportedOperationException("the command " + command + " isn't supported");
        }
    }

}
