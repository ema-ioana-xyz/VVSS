package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskIntegrationTest {
    private TasksService tasksService;
    private ArrayTaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    void add_ValidTaskObject_ReturnsAddedTask() {
        int taskListSizeBefore = taskList.size();
        Task validTask = new Task("title", new Date(2022, Calendar.MARCH, 13, 17, 0));
        Task returnedTask = tasksService.addTask(validTask);
        assertEquals(returnedTask, validTask);
        assertEquals(taskList.size(), taskListSizeBefore + 1);
    }

    @Test
    void add_InvalidTaskObject_ThrowsIllegalArgumentException() {
        int taskListSizeBefore = taskList.size();
        Task invalidTask = new Task("", new Date(2022, Calendar.MARCH, 13, 17, 0));
        assertThrows(IllegalArgumentException.class, () -> tasksService.addTask(
                invalidTask
        ));
        assertEquals(taskList.size(), taskListSizeBefore);
    }
}

