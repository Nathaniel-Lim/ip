package melodie.command;

import melodie.MelodieException;

/**
 * Lists the commands recognized by Melodie.
 */
public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    LIST,
    FIND,
    DELETE,
    BYE;

    /**
     * Converts a command word into its matching command without regard to letter case.
     *
     * @param input Command word entered by the user.
     * @return Command matching the given word.
     * @throws MelodieException If the command word is not recognized.
     */
    public static Command from(String input) throws MelodieException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MelodieException(
                    "Sorry~ I don't recognise that command :(");
        }
    }

}
