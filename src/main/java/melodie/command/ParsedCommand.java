package melodie.command;

/**
 * Contains a parsed command and its remaining arguments.
 */
public class ParsedCommand {
    private final Command command;
    private final String arguments;

    /**
     * Creates a parsed command containing a command type and its arguments.
     *
     * @param command Type of command entered by the user.
     * @param arguments Arguments supplied with the command.
     */
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
