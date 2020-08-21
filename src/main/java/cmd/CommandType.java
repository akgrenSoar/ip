package cmd;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandType {

    BYE {
        @Override
        public Consumer<List<Task>> generate(String commandParam) {
            // Nothing to do
            return (taskList) -> {};
        }
    },

    LIST {
        @Override
        public Consumer<List<Task>> generate(String commandParam) {
            return (taskList) -> { // Print all tasks in taskList
                int i = 0;
                for (Task item : taskList) {
                    System.out.println(++i + ". " + item.toString());
                }
            };
        }
    },

    DONE {
        @Override
        public Consumer<List<Task>> generate(String commandParam) {
            return (taskList) -> { // Mark task as done
                try {
                    int taskIndex = Integer.parseInt(commandParam.trim()) - 1;
                    Task task = taskList.get(taskIndex);
                    task.setCompleted(true);
                    System.out.println("\t# Done: " + task.toString());
                } catch(NumberFormatException e) {
                    System.out.println("Format Error: done {index}");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index Error: done {index}");
                }
            };
        }
    },

    DELETE {
        @Override
        public Consumer<List<Task>> generate(String commandParam) {
            return (taskList) -> { // Remove task from taskList
                try {
                    int taskIndex = Integer.parseInt(commandParam.trim()) - 1;
                    Task task = taskList.remove(taskIndex);
                    System.out.println("\t- Delete: " + task.toString());
                } catch (NumberFormatException e) {
                    System.out.println("Format Error: delete {index}");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index Error: delete {index}");
                }
            };
        }
    },

    TODO {
        @Override
        public Consumer<List<Task>> generate(String commandParam) {
            return (taskList) -> { // Add todoTask to taskList
                if (!commandParam.isBlank()) {
                    Task task = new ToDo(commandParam.trim());
                    taskList.add(task);
                    System.out.println("\t+ Add ToDo: " + task.toString());
                } else {
                    System.out.println("Format Error: ToDo {description}");
                }
            };
        }
    },

    DEADLINE {
        @Override
        public Consumer<List<Task>> generate(String commandParam) {
            return (taskList) -> { // Add deadline to taskList
                Pattern p = Pattern.compile("^(.+)\\/by(.+)$");
                Matcher m = p.matcher(commandParam);
                if (m.matches() && !m.group(1).isBlank() && !m.group(2).isBlank()) {
                    try {
                        Task task = new Deadline(m.group(1).trim(), toDate(m.group(2).trim()));
                        taskList.add(task);
                        System.out.println("\t+ Add Deadline: " + task.toString());
                    } catch (DateTimeParseException e) {
                        System.out.println("Date Error: Deadline {description} /by {yyyy-mm-dd}");
                    }
                } else {
                    System.out.println("Format Error: Deadline {description} /by {deadline}");
                }
            };
        }
    },

    EVENT {
        @Override
        public Consumer<List<Task>> generate(String commandParam) {
            return (taskList) -> { // Add event to taskList
                Pattern p = Pattern.compile("^(.+)\\/at(.+)$");
                Matcher m = p.matcher(commandParam);
                if (m.matches() && !m.group(1).isBlank() && !m.group(2).isBlank()) {
                    try {
                        Task task = new Event(m.group(1).trim(), toDate(m.group(2).trim()));
                        taskList.add(task);
                        System.out.println("\t+ Add Event: " + task.toString());
                    } catch (DateTimeParseException e) {
                        System.out.println("Date Error: Event {description} /at {yyyy-mm-dd}");
                    }
                } else {
                    System.out.println("Format Error: Event {description} /at {time}");
                }
            };
        }
    },

    UNDEFINED {
        @Override
        public Consumer<List<Task>> generate(String commandParam) {
            return (taskList) -> { // Unknown input
                System.out.println("Unknown Command!");
            };
        }
    };

    /**
     * Generate the command
     * @param commandParam Command parameters (If required)
     * @return Consumer which executes the command on given taskList
     */
    public abstract Consumer<List<Task>> generate(String commandParam);

    /**
     * Convert a CDuke command (String) to its corresponding CommandType
     *
     * @param commandName The CDukeCmd command
     * @return The corresponding CommandType
     */
    public static CommandType get(String commandName) {
        String s = commandName.toUpperCase();
        for (CommandType commandType : CommandType.values()) {
            if (s.equals(commandType.toString())) return commandType;
        }
        return CommandType.UNDEFINED;
    }

    private static LocalDate toDate(String date) {
        return LocalDate.parse(date);
    }

}
