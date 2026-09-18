package melodie;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MelodieTest {
    @Test
    public void responseStatus_errorThenSuccess_tracksLatestResponse() {
        Melodie melodie = new Melodie();

        melodie.getResponse("unknown-command");
        assertTrue(melodie.wasLastResponseError());

        melodie.getResponse("list");
        assertFalse(melodie.wasLastResponseError());
    }
}
