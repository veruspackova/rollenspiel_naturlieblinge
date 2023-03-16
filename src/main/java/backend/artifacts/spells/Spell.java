package backend.artifacts.spells;

import backend.character.Character;
import backend.character.Wizard;

import java.util.ArrayList;

public interface Spell {

    default void cast(Wizard caster, Character target) {
    }

    default void cast(Wizard caster, ArrayList<Character> targets) {
    }
}
