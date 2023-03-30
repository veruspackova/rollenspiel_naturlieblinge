package backend.enums;

import backend.artifacts.spells.*;

public enum Spells implements SpellBuilder {
    //burning hands: Attack and damage
    //fireball: attack and damage (ranged attack)
    //ray of frost: attack and damage (ranged attack)
    //mage armor: + d8 AC
    //shield: + d8 AC all on field
    //2 x heal: + d8 LP
    //sleep: no attack d4 rounds
    //magic mist: -4d on enemy attack (hides / invisible)

    BURNING_HANDS {
        @Override
        public Spell createSpell() {
            return new BurningHands();
        }
    }, FIREBALL {
        @Override
        public Spell createSpell() {
            return new Fireball();
        }
    }, RAY_OF_FROST {
        @Override
        public Spell createSpell() {
            return new RayOfFrost();
        }
    }, MAGE_ARMOUR {
        @Override
        public Spell createSpell() {
            return new MageArmour();
        }
    }, SHIELD {
        @Override
        public Spell createSpell() {
            return new Shield();
        }
    }, HEAL {
        @Override
        public Spell createSpell() {
            return new Heal();
        }
    }, SLEEP {
        @Override
        public Spell createSpell() {
            return new Sleep();
        }
    }, MAGIC_MIST {
        @Override
        public Spell createSpell() {
            return new MagicMist();
        }
    }, SECOND_LIFE {
        @Override
        public Spell createSpell() {
            return new SecondLife();
        }
    },
    ;

}

interface SpellBuilder {
    Spell createSpell();
}
