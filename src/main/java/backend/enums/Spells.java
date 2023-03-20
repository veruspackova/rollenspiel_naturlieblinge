package backend.enums;

import backend.artifacts.spells.*;

public enum Spells {
    //burning hands: Attack and damage
    //fireball: attack and damage (ranged attack)
    //ray of frost: attack and damage (ranged attack)
    //mage armor: + d8 AC
    //shield: + d8 AC all on field
    //2 x heal: + d8 LP
    //sleep: no attack d4 rounds
    //magic mist: -4d on enemy attack (hides / invisible)

    BURNING_HANDS(BurningHands.class),
    FIREBALL(Fireball.class),
    RAY_OF_FROST(RayOfFrost.class),
    MAGE_ARMOUR(MageArmour.class),
    SHIELD(Shield.class),
    HEAL(Heal.class),
    SLEEP(Sleep.class),
    MAGIC_MIST(MagicMist.class),
    ;

    private final Class<? extends Spell> spellName;

    Spells(Class<? extends Spell> spellName) {
        this.spellName = spellName;
    }

    public Class<? extends Spell> getSpellName() {
        return spellName;
    }
}
