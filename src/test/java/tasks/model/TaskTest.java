package tasks.model;

import org.junit.jupiter.api.*;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for Task class")
@Tag("TaskTests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Timeout(20)
class TaskTest {
    private ArrayTaskList list;

    @BeforeEach
    void setUp() {
        list = new ArrayTaskList();
    }

    @Test
    @DisplayName("Add Valid Task ECP")
    @Order(1)
    void testEPC_0() throws ParseException {
        var task = new Task(
                "Acesta este un task valid",
                Task.getDateFormat().parse("2024-03-29 10:10"),
                Task.getDateFormat().parse("2024-03-30 10:10"),
                30
        );
        task.setActive(false);


        list.add(task);

        assertEquals(task, list.getTask(0));
    }

    @Test
    @DisplayName("Add Invalid Task ECP 1")
    @Order(2)
    void testEPC_1() throws ParseException {
        var task = new Task(
                null,
                Task.getDateFormat().parse("2024-03-29 10:10"),
                Task.getDateFormat().parse("2024-03-30 10:10"),
                30
        );
        task.setActive(false);

        assertThrows(IllegalArgumentException.class, () -> list.add(task));
    }

    @Test
    @DisplayName("Add Invalid Task ECP 2")
    @Order(3)
    void testEPC_2() throws ParseException {
        var task = new Task(
                "",
                Task.getDateFormat().parse("2024-03-29 10:10"),
                Task.getDateFormat().parse("2024-03-30 10:10"),
                30
        );
        task.setActive(false);

        assertThrows(IllegalArgumentException.class, () -> list.add(task));
    }

    @Test
    @DisplayName("Add Invalid Task ECP 3")
    @Order(4)
    void testEPC_3() throws ParseException {
        var task = new Task(
                "Un titlu",
                Task.getDateFormat().parse("2024-03-29 10:10"),
                Task.getDateFormat().parse("2022-01-18 10:10"),
                30
        );
        task.setActive(false);

        assertThrows(IllegalArgumentException.class, () -> list.add(task));
    }

    @Test
    @DisplayName("Add Valid Task BVA 1")
    @Order(5)
    void testBVA_0() throws ParseException {
        var task = new Task(
                "T",
                Task.getDateFormat().parse("2024-03-29 10:10"),
                Task.getDateFormat().parse("2024-03-30 10:10"),
                30
        );
        task.setActive(false);

        list.add(task);

        assertEquals(task, list.getTask(0));
    }

    @Test
    @DisplayName("Add Valid Task BVA 2")
    @Order(6)
    void testBVA_1() throws ParseException {
        var task = new Task(
                "Acesta este un task valid",
                Task.getDateFormat().parse("1971-01-01 10:10"),
                Task.getDateFormat().parse("1971-01-02 10:10"),
                30
        );
        task.setActive(false);

        list.add(task);

        assertEquals(task, list.getTask(0));
    }

    @Test
    @DisplayName("Add Invalid Task BVA 1")
    @Order(7)
    void testBVA_2() throws ParseException {
        var task = new Task(
                null,
                Task.getDateFormat().parse("2024-03-29 10:10"),
                Task.getDateFormat().parse("2024-03-30 10:10"),
                30
        );
        task.setActive(false);

        assertThrows(IllegalArgumentException.class, () -> list.add(task));
    }

    @Test
    @DisplayName("Add Invalid Task BVA 2")
    @Order(8)
    void testBVA_3() throws ParseException {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Task(
                "Un task",
                Task.getDateFormat().parse("1969-01-01 10:10"),
                Task.getDateFormat().parse("1969-01-02 10:10"),
                30
        ));
    }


    @AfterEach
    void tearDown() {
    }
}