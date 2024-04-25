package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTaskListUnitTests {
    private ArrayTaskList arrayTaskList;

    @BeforeEach
    public void setup(){
        arrayTaskList = new ArrayTaskList();
    }

    @Test
    public void add_valid_task(){
        Task task = new Task("title", new Date(2024, Calendar.MARCH, 13, 17, 0), new Date(2024, Calendar.MARCH, 15, 17, 0), 5);
        int sizeBeforeAdd = arrayTaskList.size();

        arrayTaskList.add(task);
        assertEquals(sizeBeforeAdd + 1, arrayTaskList.size());
    }

    @Test
    public void remove_task(){
        Task task1 = new Task("title", new Date(2024, Calendar.MARCH, 13, 17, 0), new Date(2024, Calendar.MARCH, 15, 17, 0), 5);
        Task task2 = new Task("title", new Date(2024, Calendar.MARCH, 13, 17, 0), new Date(2024, Calendar.MARCH, 15, 17, 0), 5);
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);

        arrayTaskList.remove(task1);

        assertEquals(1, arrayTaskList.size());
    }

}
