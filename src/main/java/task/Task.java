package task;

import java.util.Objects;

/**
 * A task is a is an item that has a description and is completable
 */
public abstract class Task {

    private static final String SYMBOL_DONE = "✓";
    private static final String SYMBOL_UNDONE = "✗";

    private boolean completed;
    private String description;

    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

    public Task(boolean completed, String description) {
        this.completed = completed;
        this.description = description;
    }

    private String isCompletedSymbol() {
        return this.completed ? SYMBOL_DONE : SYMBOL_UNDONE;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return isCompleted() == task.isCompleted() &&
                getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCompleted(), getDescription());
    }

    @Override
    public String toString() {
        return "[" + isCompletedSymbol() + "] " + description;
    }

    public String toCSV() {
        return "" + this.isCompleted() + ',' + this.description;
    }

}
