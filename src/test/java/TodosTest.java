import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchForTasksByQueryInSimpleTaskIfTrue() {
        SimpleTask simpleTask = new SimpleTask(3, "Написать письмо Коле");

        boolean expected = true;
        boolean actual = simpleTask.matches("Написать");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchForTasksByQueryInSimpleTaskIfFalse() {
        SimpleTask simpleTask = new SimpleTask(3, "Написать письмо Коле");

        boolean expected = false;
        boolean actual = simpleTask.matches("Позвонить");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchForTasksByQueryInEpicIfTrue() {
        String[] subtasks = { "Мороженное", "Хлеб", "Молоко", "Масло" };
        Epic epic = new Epic(36, subtasks);

        boolean expected = true;
        boolean actual = epic.matches("Молоко");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchForTasksByQueryInEpicIfFalse() {
        String[] subtasks = { "Мороженное", "Хлеб", "Молоко", "Масло" };
        Epic epic = new Epic(36, subtasks);

        boolean expected = false;
        boolean actual = epic.matches("Яблоки");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchForTasksByQueryInMeetingIfTrueInTopic() {
        Meeting meeting = new Meeting(
                356,
                "Планирование отпуска",
                "Каникулы",
                "15 мая в 19:00"
        );

        boolean expected = true;
        boolean actual = meeting.matches("Планирование");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchForTasksByQueryInMeetingIfTrueInProject() {
        Meeting meeting = new Meeting(
                356,
                "Планирование отпуска",
                "Каникулы",
                "15 мая в 19:00"
        );

        boolean expected = true;
        boolean actual = meeting.matches("Каникулы");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchForTasksByQueryInMeetingIfFalse() {
        Meeting meeting = new Meeting(
                356,
                "Планирование отпуска",
                "Каникулы",
                "15 мая в 19:00"
        );

        boolean expected = false;
        boolean actual = meeting.matches("Ремонт");

        Assertions.assertEquals(expected, actual);
    }

}
