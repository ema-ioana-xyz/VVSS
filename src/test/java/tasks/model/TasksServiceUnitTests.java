package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.MockitoAnnotations;

public class TasksServiceUnitTests {

    @Mock
    private ArrayTaskList arrayTaskList;

    private TasksService tasksService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        tasksService = new TasksService(arrayTaskList);
    }

    @Test
    public void test_add_valid_task_ReturnsAddedTask(){
        Task task = new Task("title", new Date(2024, Calendar.MARCH, 13, 17, 0), new Date(2024, Calendar.MARCH, 15, 17, 0), 5);

        Mockito.doNothing().when(arrayTaskList).add(task);

        Task returnedTask = tasksService.addTask(task);

        assertEquals(task, returnedTask);
    }

    @Test
    public void get_interval_in_hours_Returns00() {
        Task task = new Task("title", new Date(2024, Calendar.MARCH, 13, 17, 0), new Date(2024, Calendar.MARCH, 15, 17, 0), 5);

        String result = tasksService.getIntervalInHours(task);

        assertEquals("00:00", result);
    }
}
