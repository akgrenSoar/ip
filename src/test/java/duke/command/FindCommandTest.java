package duke.command;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    private final List<Task> taskList = new ArrayList<>(1);

    @Test
    void execute_success() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Populate taskList with 1 item
        taskList.add(new ToDo("Eat some chips"));

        // Execute the FindCommand
        new FindCommand(taskList, "t some c").execute();

        String expectedOutput = "Here are the matching tasks in your list:\r\n"
                + "1. [T][✗] Eat some chips\r\n"
                + "Number of tasks found: 1\r\n";

        // Check output is same as expected
        assertEquals(expectedOutput, outContent.toString());

        System.setOut(originalOut);
    }
}
