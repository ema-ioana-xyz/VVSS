package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import tasks.model.ArrayTaskList;
import tasks.model.Task;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class TaskServiceTest {
    private TasksService service;
    private ObservableList<Task> observableList;

    @BeforeEach
    void setUp() {
        ArrayTaskList taskList = new ArrayTaskList();
        observableList = FXCollections.observableArrayList(taskList.getAll());
        service = new TasksService(taskList);
    }

    @Test
    @DisplayName("F02_TC01")
    void testFilterTasksInvalidDates() throws ParseException {
        Task t1 = new Task("T", Task.getDateFormat().parse("2022-04-02 12:00"),
                Task.getDateFormat().parse("2022-04-05 12:00"), 30);
        observableList.add(t1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = formatter.parse("2022-04-02 12:00");
        Date end = formatter.parse("2022-04-01 12:00");
        Iterable<Task> tasks = service.filterTasks(observableList, start, end);
        List<Task> filtered = StreamSupport.stream(tasks.spliterator(),false).collect(Collectors.toList());
        Assertions.assertEquals(0, filtered.size());
    }

    @Test
    @DisplayName("F02_TC02")
    void testFilterTasksEmptyList() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = formatter.parse("2022-04-02 12:00");
        Date end = formatter.parse("2022-04-01 12:00");
        Iterable<Task> tasks = service.filterTasks(observableList, start, end);
        List<Task> filtered = StreamSupport.stream(tasks.spliterator(),false).collect(Collectors.toList());
        Assertions.assertEquals(filtered.size(),0);
    }

    @Test
    @DisplayName("F02_TC03")
    void testFilterTasksNonRepetitiveTask() throws ParseException{
        Task t1 = new Task("T", Task.getDateFormat().parse("2022-04-03 12:00"));
        observableList.add(t1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = formatter.parse("2022-04-02 12:00");
        Date end = formatter.parse("2022-04-03 12:00");
        Iterable<Task> tasks = service.filterTasks(observableList, start, end);
        List<Task> filtered = StreamSupport.stream(tasks.spliterator(),false).collect(Collectors.toList());
        Assertions.assertEquals(filtered.size(),0);
    }

    @Test
    @DisplayName("F02_TC04")
    void testFilterTasksStartDateNotInInterval() throws ParseException{
        Task t1 = new Task("T", Task.getDateFormat().parse("2022-04-04 12:00"),
                Task.getDateFormat().parse("2022-04-05 12:00"), 30);
        t1.setActive(true);
        observableList.add(t1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = formatter.parse("2022-04-02 12:00");
        Date end = formatter.parse("2022-04-03 12:00");
        Iterable<Task> tasks = service.filterTasks(observableList, start, end);
        List<Task> filtered = StreamSupport.stream(tasks.spliterator(),false).collect(Collectors.toList());
        Assertions.assertEquals(filtered.size(),0);
    }

    @Test
    @DisplayName("F02_TC05")
    void filterTasksStartDateInInterval() throws ParseException {
        Task t1 = new Task("T", Task.getDateFormat().parse("2022-04-04 12:00"),
                Task.getDateFormat().parse("2022-04-05 12:00"), 30);
        t1.setActive(true);
        observableList.add(t1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = formatter.parse("2022-04-02 12:00");
        Date end = formatter.parse("2022-04-04 12:00");
        Iterable<Task> tasks = service.filterTasks(observableList, start, end);
        List<Task> filtered = StreamSupport.stream(tasks.spliterator(),false).collect(Collectors.toList());
        Assertions.assertEquals(filtered.size(),1);
        Assertions.assertEquals(filtered.get(0),t1);
    }

    @Test
    @DisplayName("F02_TC06")
    void filterTasksNotValid() throws ParseException{
        Task t1 = new Task("T", Task.getDateFormat().parse("2022-04-03 12:00"),
                Task.getDateFormat().parse("2022-04-06 12:00"), 30);
        t1.setActive(true);
        observableList.add(t1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = formatter.parse("2022-04-02 12:00");
        Date end = formatter.parse("2022-04-04 12:30");
        Iterable<Task> tasks = service.filterTasks(observableList, start, end);
        List<Task> filtered = StreamSupport.stream(tasks.spliterator(),false).collect(Collectors.toList());
        Assertions.assertEquals(filtered.size(),1);
        Assertions.assertEquals(filtered.get(0),t1);
    }
}