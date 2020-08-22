package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DukeDateTime class keeps track of a LocalDateTime
 */
public class DukeDateTime implements Comparable<DukeDateTime> {

    private final LocalDateTime localDateTime;

    private DukeDateTime(LocalDateTime localDate) {
        this.localDateTime = localDate;
    }

    public LocalDateTime get() {
        return this.localDateTime;
    }

    /**
     * Generate a DukeDate representing a specified datetime
     * e.g. "01112020 1800" means "01 Nov 2020 06:00pm"
     *
     * @param dateTime The dateTime in the format "ddMMyyyy HHmm"
     * @return A DukeDateTime representing the specified dateTime
     * @throws DateTimeParseException If the dateTime does not follow the specified format
     */
    public static DukeDateTime generate(String dateTime) throws DateTimeParseException {
        // Build a localDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return new DukeDateTime(localDateTime);
    }

    /**
     * Get the String representation of DukeDate
     * Format is "dd MMM yyyy KK:mma"
     * (e.g. 18 May 2020 08:20pm)
     *
     * @return The date represented by DukeDate
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy KK:mma");
        return this.localDateTime.format(formatter);
    }

    @Override
    public int compareTo(DukeDateTime o) {
        return this.localDateTime.compareTo(o.get());
    }
}
