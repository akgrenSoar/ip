package task;

import java.time.LocalDateTime;

/**
 * A Deadline is a Task with a deadline
 */
public class Deadline extends Task {

    private DukeDateTime deadline;

    public Deadline(String description, DukeDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline.get();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

}
