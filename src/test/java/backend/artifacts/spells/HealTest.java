package backend.artifacts.spells;

import backend.character.Wizard;
import backend.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealTest {

    @Test
    void testCast() {
        Wizard wizard = new Wizard(Race.HUM, "Wizard");
        int initHP = wizard.getHitPoints();

        ArrayList targets = new ArrayList<>();
        targets.add(wizard);

        Heal spell = new Heal();
        spell.castSpell(wizard, targets);

        assertTrue(wizard.getArmourClass() > initHP);
    }
}
