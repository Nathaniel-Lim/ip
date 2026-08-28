package melodie.command;

import melodie.MelodieException;
import melodie.task.Deadline;
import melodie.task.Event;
import melodie.task.Task;
import melodie.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseTask_validTodo_returnsTodo() throws MelodieException {
        // Arrange
        Parser parser = new Parser();
        ParsedCommand parsedCommand = parser.parse("todo play Yunara jungle");

        // Act
        Task actualTask = parser.parseTask(parsedCommand);

        // Assert
        assertInstanceOf(Todo.class, actualTask);
        assertEquals("play Yunara jungle", actualTask.getDescription());
        assertFalse(actualTask.isCompleted());
        assertEquals("[T][ ] play Yunara jungle", actualTask.toString());
    }

    @Test
    public void parseTask_validDeadline_returnsFormattedDeadline() throws MelodieException {
        Parser parser = new Parser();
        ParsedCommand parsedCommand = parser.parse("deadline lose 500 games /by 8/12/2026 2359");

        Task actualTask = parser.parseTask(parsedCommand);

        assertInstanceOf(Deadline.class, actualTask);
        assertEquals("lose 500 games", actualTask.getDescription());
        assertFalse(actualTask.isCompleted());
        assertEquals("[D][ ] lose 500 games (by: Dec 08 2026, 11:59 PM)", actualTask.toString());
    }

    @Test
    public void parseTask_validEvent_returnsFormattedEvent() throws MelodieException {
        Parser parser = new Parser();
        ParsedCommand parsedCommand =
                parser.parse("event pull C6R1 Nicole /from 16/12/2026 1100 /to 6/1/2027 2359");

        Task actualTask = parser.parseTask(parsedCommand);

        assertInstanceOf(Event.class, actualTask);
        assertEquals("pull C6R1 Nicole", actualTask.getDescription());
        assertFalse(actualTask.isCompleted());
        assertEquals("[E][ ] pull C6R1 Nicole (from: Dec 16 2026, 11:00 AM " +
                "to: Jan 06 2027, 11:59 PM)", actualTask.toString());
    }

    @Test
    public void parse_mixedCaseCommand_returnsMatchingCommand() throws MelodieException {
        Parser parser = new Parser();

        ParsedCommand actualCommand = parser.parse("ToDo int before elder drag");

        assertEquals(Command.TODO, actualCommand.getCommand());
        assertEquals("int before elder drag", actualCommand.getArguments());
    }

    @Test
    public void parseTask_invalidDate_throwsMelodieException() {
        Parser parser = new Parser();
        ParsedCommand parsedCommand = new ParsedCommand(
                Command.DEADLINE, "farm 600 Intertwined Fate /by 67/67/2067 1800");

        MelodieException exception = assertThrows(
                MelodieException.class,
                () -> parser.parseTask(parsedCommand));
        // nullary function is used to ensure parseTask doesn't throw before assertThrows does
        assertEquals("Please enter the date and time in d/M/yyyy HHmm format :(\n"
                + "    Example: 2/12/2019 1800", exception.getMessage());
    }

    @Test
    public void parseTask_eventEndsBeforeStart_throwsMelodieException() {
        Parser parser = new Parser();
        ParsedCommand parsedCommand = new ParsedCommand(
                Command.EVENT,
                "try not to get F for CS2013T /from 10/2/2027 2300 /to 10/2/2027 1800");

        MelodieException exception = assertThrows(
                MelodieException.class,
                () -> parser.parseTask(parsedCommand));
        assertEquals("The event cannot end before it starts :(", exception.getMessage());
    }

    @Test
    public void parseTask_missingDescription_throwsMelodieException() {
        Parser parser = new Parser();
        ParsedCommand parsedCommand = new ParsedCommand(Command.TODO, "");

        MelodieException exception = assertThrows(
                MelodieException.class,
                () -> parser.parseTask(parsedCommand));
        assertEquals("You can't leave the description of a todo empty :(", exception.getMessage());
    }
}
