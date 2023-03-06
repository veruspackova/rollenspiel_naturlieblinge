package backend.artifacts;

import java.util.ArrayList;
import backend.character.Character;

public interface ISearchable {
    public ArrayList<ISearchable> search(Character character);
}
