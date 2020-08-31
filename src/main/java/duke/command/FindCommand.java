package duke.command;

import duke.task.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * List all tasks which containing searchString
 */
public class FindCommand implements Command {

    private final List<Task> taskList;
    private final String searchString;

    /**
     * Create a FindCommand to search for specified searchString within the taskList
     * @param taskList to search for searchString
     * @param searchString The string of search interest
     */
    public FindCommand(List<Task> taskList, String searchString) {
        this.taskList = taskList;
        this.searchString = searchString;
    }

    /**
     * List all tasks containing searchString
     */
    @Override
    public void execute() {
        System.out.println("Here are the matching tasks in your list:");

        // Search matching tasks
        AtomicInteger index = new AtomicInteger(0);
        this.taskList.stream()
                .filter(task -> task.getDescription().contains(searchString))
                .forEach(task -> System.out.println(index.incrementAndGet() + ". " + task.toString()));

        // Let user know how many items are found, so that program is responsive in case no items are found
        System.out.println("Number of tasks found: " + index);
    }

}
