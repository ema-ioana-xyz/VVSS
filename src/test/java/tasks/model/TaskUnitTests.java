package tasks.model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class TaskUnitTests {

    @Test
    public void test_next_time_after_ReturnsNull(){
        Task task = new Task("title", new Date(2024, Calendar.MARCH, 13, 17, 0), new Date(2024, Calendar.MARCH, 15, 17, 0), 5);

        assert task.nextTimeAfter(new Date(2024, Calendar.MARCH, 16, 17, 0)) == null;
    }
}
