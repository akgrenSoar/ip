package task;

import java.time.LocalDate;

public class Event extends Task{

    private LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.toString() + ")";
    }
}
