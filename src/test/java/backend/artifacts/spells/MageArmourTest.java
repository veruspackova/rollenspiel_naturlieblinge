package backend.artifacts.spells;

import backend.character.Wizard;
import backend.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MageArmourTest {

    @Test
    void testCast() {
        Wizard wizard = new Wizard(Race.HUM, "Wizard");
        int initAC = wizard.getArmourClass();

        ArrayList targets = new ArrayList<Character>();
        targets.add(wizard);

        MageArmour mg = new MageArmour();
        mg.cast(wizard, targets);

        assertTrue(wizard.getArmourClass() > initAC);
    }
}
