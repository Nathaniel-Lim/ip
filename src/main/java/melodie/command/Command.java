package melodie.command;

import melodie.MelodieException;

public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    LIST,
    DELETE,
    BYE;

    public static Command from(String input) throws MelodieException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MelodieException(
                    "Sorry~ I don't recognise that command :("
            );
        }
    }

}
