package backend.character;

import backend.enums.Race;
import backend.enums.Stat;

import java.util.HashMap;
import java.util.Map;

public class RaceStatBonusHelper {
    Map<Race, Map<Stat, Integer>> raceStatMap;

    public RaceStatBonusHelper() {
        Map<Race, Map<Stat, Integer>> raceStatMap = new HashMap<>();
        Map<Stat, Integer> statMapHuman = new HashMap<>();
        statMapHuman.put(Stat.STR, 1);
        statMapHuman.put(Stat.DEX, 1);
        statMapHuman.put(Stat.CON, 1);
        statMapHuman.put(Stat.WIS, 1);
        statMapHuman.put(Stat.INT, 1);
        raceStatMap.put(Race.HUM, statMapHuman);

        Map<Stat, Integer> statMapDwarf = new HashMap<>();
        statMapDwarf.put(Stat.STR, 2);
        statMapDwarf.put(Stat.CON, 2);
        statMapDwarf.put(Stat.WIS, 1);
        raceStatMap.put(Race.DWA, statMapDwarf);

        Map<Stat, Integer> statMapHobbit = new HashMap<>();
        statMapHobbit.put(Stat.WIS, 2);
        statMapHobbit.put(Stat.DEX, 2);
        statMapHobbit.put(Stat.CON, 1);
        raceStatMap.put(Race.HOB, statMapHobbit);

        Map<Stat, Integer> statMapElf = new HashMap<>();
        statMapElf.put(Stat.INT, 2);
        statMapElf.put(Stat.DEX, 2);
        statMapElf.put(Stat.WIS, 1);
        raceStatMap.put(Race.ELF, statMapElf);

        raceStatMap.put(Race.NONE, new HashMap<>());

        this.raceStatMap = raceStatMap;
    }

    public void addStatBonuses(Character character) {
        Race race = character.getRace();
        Map<Stat, Integer> statMap = raceStatMap.get(race);

        for (Stat stat : Stat.values()) {
            if (statMap.containsKey(stat)) {
                int newValue = character.getStat(stat) + statMap.get(stat);
                character.setStat(stat, newValue);
            }
        }
    }
}
