package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ToDoTest {

    @Test
    void hashCode_equals() {
        Task case1 = new ToDo("test");
        Task case2 = new ToDo("test");
        assertEquals(case1.hashCode(), case2.hashCode());
    }

    @Test
    void hashCode_notEquals() {
        Task case1 = new ToDo(true,"test");
        Task case2 = new ToDo(false, "test");
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new ToDo(true,"test case1");
        case2 = new ToDo(true, "test case2");
        assertNotEquals(case1.hashCode(), case2.hashCode());
    }

    @Test
    void toCSV_fromCSV() {
        Task task1 = new ToDo("test");
        Task task2 = ToDo.fromCSV(task1.toCSV());
        assertEquals(task1.hashCode(), task2.hashCode());
    }

}