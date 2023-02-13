package backend.enums;

/**
 * Character Race
 * <p>
 *     One race is set at character creation. <br>
 *     Race has a impact on Character Abilities,
 *     with each race offering different bonuses.
 * </p>
 *
 * @author oleablossom
 */
public enum Race {
    /**
     * No specified race
     * (no bonus to any stat)
     */
    NONE,

    /**
     * Human
     * (all-rounder)
     */
    HUM,

    /**
     * Dwarf
     * (strong, sturdy, wise)
     */
    DWA,

    /**
     * Elf
     * (agile, intelligent, wise)
     */
    ELF,

    /**
     * Hobbit
     * (agile, wise, sturdy)
     */
    HOB,
}
