package cmd;

import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command line application for Duke
 */
public class CDuke {

    private static final String LOGO = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\nLet's have a conversation!";
    private static final String ENDING_GREETING = "Bye. Hope to see you again soon!";

    private static final Pattern INPUT_PATTERN = Pattern.compile("^\\s*(\\S+)\\s*(.*)$");

    private final List<Task> taskList;
    private final Scanner scanner;

    public CDuke() {
        this.taskList = new ArrayList<>(100);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main logic for Duke commandline
     */
    public void run() {

        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);

        while(true) {

            // Prompt for input
            String input = scanner.nextLine();
            Matcher matcher = INPUT_PATTERN.matcher(input);

            // No input received, skip
            if (!matcher.matches()) continue;

            // Look up command and execute
            CommandType type = CommandType.get(matcher.group(1));
            Consumer<List<Task>> exec = type.generate(matcher.group(2));
            exec.accept(taskList);

            // Exit CDuke
            if (type.equals(CommandType.BYE)) {
                break;
            }
        }

        System.out.println(ENDING_GREETING);
    }
}
