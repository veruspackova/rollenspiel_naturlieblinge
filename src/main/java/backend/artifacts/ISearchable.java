package backend.artifacts;

import java.util.ArrayList;

public interface ISearchable {
    public ArrayList<ISearchable> search(Character character);
}
