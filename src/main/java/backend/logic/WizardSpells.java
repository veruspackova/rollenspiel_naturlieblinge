package backend.logic;

import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Stat;

public class WizardSpells {
    //burning hands: Attack and damage
    //mage armor: + d8 AC
    //2 x heal: + d8 LP
    //shield: + d8 AC all on field
    //sleep: no attack d4 rounds
    //magic mist: -4d on enemy attack (hides / invisible)
    //fireball: attack and damage (ranged attack)
    //ray of frost: attack and damage (ranged attack)

    private Character player;
    private int slotsBurningHands;
    private int slotsMageArmour;

    public WizardSpells(Wizard player) {
        this.player = player;
    }

    /**
     * the difficulty class for other characters, when trying to defend against the wizard's attack
     * Spell save DC = 8 + your proficiency bonus + your Intelligence modifier
     *
     * @return difficulty class
     */
    public int getSpellSaveDC() {
        return 8 + player.getProficiencyBonus() + player.getStatModifier(Stat.INT);
    }

    public void initialiseSpellSlots() {
        slotsBurningHands = 1;
        slotsMageArmour = 1;
    }

    /**
     * Each creature in a 15-foot cone must make a Dexterity saving throw.
     * A creature takes 3d6 fire damage on a failed save,
     * or half as much damage on a successful one.
     *
     * @return up to 18 damage
     */
    public int castBurningHands(Character target) {
        int damage = 0;

        if (slotsBurningHands > 0) {
            Dice d6 = new Dice(6);
            damage = d6.roll() + d6.roll() + d6.roll();

            slotsBurningHands -= 1;
        }

        // the DC for a saving throw allowed by a spell
        // is determined by the caster’s spellcasting ability and proficiency bonus
        // If the total equals or exceeds the target number, the saving throw is a success
        if (target.getSavingThrowSuccessful(Stat.DEX, getSpellSaveDC())) {
            damage = damage / 2;
        }

        return damage;
    }

    /**
     * Some spells require the caster to make an attack roll.<br>
     * Spell attack modifier = proficiency bonus + Intelligence modifier
     *
     * @param targetAC difficulty of hitting the intended target
     * @return true, if the spell effect hits the intended target
     */
    public boolean isAttackSuccessful(int targetAC) {
        Dice d20 = new Dice(20);
        return d20.roll() >= targetAC + player.getProficiencyBonus() + player.getStatModifier(Stat.INT);
    }

}
