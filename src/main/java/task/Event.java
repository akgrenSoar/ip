package task;

import java.time.LocalDateTime;

/**
 * An Event is a Task with startTime, and endTime
 */
public class Event extends Task{

    private DukeDateTime eventStart;
    private DukeDateTime eventEnd;

    public Event(String description, DukeDateTime eventStart, DukeDateTime eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public LocalDateTime getStart() {
        return this.eventStart.get();
    }

    public LocalDateTime getEnd() {
        return this.eventEnd.get();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart + " till: " + eventEnd + ")";
    }
}
