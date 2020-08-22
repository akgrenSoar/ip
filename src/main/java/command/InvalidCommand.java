package command;

import task.Task;

import java.util.List;

/**
 * TODO: Convert invalid command to Duke Exception
 */
public class InvalidCommand extends Command {

    private final String message;

    public InvalidCommand() {
        this.message = "Unrecognised Command!";
    }

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public boolean isModifying() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println(this.message);
    }

    @Override
    public void undo() {
        // Operation unsupported
        System.out.println("Undo: InvalidCommand");
    }
}