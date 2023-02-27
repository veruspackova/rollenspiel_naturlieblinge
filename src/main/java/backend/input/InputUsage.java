package backend.input;

import backend.enums.GameRoundAction;
import backend.logic.GameRoundLogic;

import java.util.ArrayList;

public class InputUsage {

    private String target; //@todo change type to fit maybe enum
    private GameRoundLogic logic;

    public InputUsage(GameRoundLogic logic)
    {
        target = "GameRoundAction";
        this.logic = logic;
    }

    public void action(ArrayList<String> input)
    {
        for (String i:input)
            if (target == "GameRoundAction")
            {
                switch (i)
                {
                    case "fight" -> {
                        target = "weapon";
                        logic.play(GameRoundAction.fight);
                    }
                }
            }

    }
}
