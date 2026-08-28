package melodie.command;

/**
 * Contains a parsed command and its remaining arguments.
 */
public class ParsedCommand {
    private final Command command;
    private final String arguments;

    public ParsedCommand(Command command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public Command getCommand() {
        return this.command;
    }

    public String getArguments() {
        return this.arguments;
    }
}
