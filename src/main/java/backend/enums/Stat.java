package backend.enums;

/**
 * Character Abilities (or Stats)
 * <p>
 *     Each ability is assigned a score at character creation. <br>
 *     Abilities impact many actions, for example,
 *     by adding a bonus to a dice roll or setting a minimum roll for success.
 * </p>
 *
 * @author oleablossom
 */
public enum Stat {
    /**
     * Strength
     * (physical power)
     */
    STR,

    /**
     * Dexterity
     * (agility)
     */
    DEX,

    /**
     * Constitution
     * (endurance)
     */
    CON,

    /**
     * Intelligence
     * (reasoning and memory)
     */
    INT,

    /**
     * Wisdom
     * (perception and insight)
     */
    WIS,

    /**
     * Charisma
     * (force of personality)
     * <p>
     * not used here, for some reason? no idea why this is ignored,
     * but Intelligence and Wisdom can't be collapsed into one ability score ...
     */
    CHA,
}
