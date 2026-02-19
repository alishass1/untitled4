import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testSimpleTaskMatchesWhenQueryInTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");

        boolean result = task.matches("родителям");

        Assertions.assertTrue(result);
    }

    @Test
    public void testSimpleTaskNotMatchesWhenQueryNotInTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");

        boolean result = task.matches("друзьям");

        Assertions.assertFalse(result);
    }


    @Test
    public void testSimpleTaskMatchesWithPartialWord() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");

        boolean result = task.matches("дите");

        Assertions.assertTrue(result);
    }

    @Test
    public void testEpicMatchesWhenQueryInSubtask() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean result = epic.matches("Яйца");

        Assertions.assertTrue(result);
    }

    @Test
    public void testEpicMatchesWhenQueryInMultipleSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean result = epic.matches("о");

        Assertions.assertTrue(result);
    }

    @Test
    public void testEpicNotMatchesWhenQueryNotInSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        boolean result = epic.matches("Сыр");

        Assertions.assertFalse(result);
    }

    @Test
    public void testEpicWithEmptySubtasks() {
        String[] subtasks = {};
        Epic epic = new Epic(55, subtasks);

        boolean result = epic.matches("что-то");

        Assertions.assertFalse(result);
    }

    @Test
    public void testMeetingMatchesWhenQueryInTopic() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean result = meeting.matches("Выкатка");

        Assertions.assertTrue(result);
    }

    @Test
    public void testMeetingMatchesWhenQueryInProject() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean result = meeting.matches("НетоБанка");

        Assertions.assertTrue(result);
    }

    @Test
    public void testMeetingMatchesWhenQueryInBoth() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean result = meeting.matches("приложение");

        Assertions.assertTrue(result);
    }

    @Test
    public void testMeetingNotMatchesWhenQueryNotInTopicOrProject() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean result = meeting.matches("понедельник");

        Assertions.assertFalse(result);
    }

    @Test
    public void testMeetingCaseSensitivity() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        boolean result = meeting.matches("выкатка");

        Assertions.assertFalse(result);
    }
}