package ru.netology;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");
        assertArrayEquals(expected, actual);

        expected = new Task[]{epic};
        actual = todos.search("Яйца");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMultipleTasks() {
        SimpleTask simpleTask1 = new SimpleTask(1, "Позвонить родителям");
        SimpleTask simpleTask2 = new SimpleTask(2, "Позвонить друзьям");
        Epic epic = new Epic(3, new String[]{"Позвонить коллеге", "Отправить отчёт"});

        Todos todos = new Todos();
        todos.add(simpleTask1);
        todos.add(simpleTask2);
        todos.add(epic);

        Task[] expected = {simpleTask1, simpleTask2, epic};
        Task[] actual = todos.search("Позвонить");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindExactlyOneTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Epic epic = new Epic(2, new String[]{"Купить молоко"});
        Meeting meeting = new Meeting(3, "Встреча с клиентом", "Проект X", "Завтра в 10:00");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("клиентом");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNoTasks() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Epic epic = new Epic(2, new String[]{"Купить молоко"});
        Meeting meeting = new Meeting(3, "Встреча с клиентом", "Проект X", "Завтра в 10:00");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Неизвестный запрос");
        assertArrayEquals(expected, actual);
    }
}
