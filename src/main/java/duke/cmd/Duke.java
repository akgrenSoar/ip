package duke.cmd;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.parser.Parser;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Command line application for Duke (Command line UI)
 */
public class Duke {

    private static final String LOGO = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\nWhat can i do for you?";
    private static final String ENDING_GREETING = "Bye. Hope to see you again soon!";

    private final List<Task> taskList;
    private final Scanner scanner;

    public Duke() {
        this.taskList = new ArrayList<>(100);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main execution point for Duke commandline
     * Deals with main logic and IO
     */
    public void run() {

        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);

        while (true) {

            // Prompt for input
            String input = scanner.nextLine();
            if (input.isBlank()) continue;

            // Parse command
            Command command = Parser.parse(taskList, input);

            // Received exit command
            if (command instanceof ExitCommand) break;

            // Execute command
            command.execute();
        }

        System.out.println(ENDING_GREETING);
    }
}
