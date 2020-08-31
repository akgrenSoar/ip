package duke.command;

import duke.parser.CommandFactory;

import java.util.Arrays;

/**
 * List all available Commands
 */
public class HelpCommand implements Command {

    /**
     * List all available Commands
     */
    @Override
    public void execute() {
        System.out.println("Command list:");

        // CommandFactory is an enum of all available commands, simply print them
        Arrays.stream(CommandFactory.values())
                .map((p) -> " " + p.toString().toLowerCase())
                .forEach(System.out::print);

        System.out.println();
    }

}
