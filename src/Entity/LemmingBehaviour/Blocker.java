package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;

import java.util.ArrayList;


public class Blocker extends LemmingBehaviour
{
    public Blocker (Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public boolean update ()
    {
        if (lemming.findInferiorBlock())
        {
            changeLemmingDirection(lemming.getSideLemmings());
            return true;
        }
        else
        {
            lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
            return false;
        }
    }

    private void changeLemmingDirection (ArrayList<Lemming> lemmings)
    {
        for (Lemming l : lemmings)
        {
            l.changeDirectionTo((l.getX() == lemming.getX() + 1) ? DirectionEnum.RIGHT : DirectionEnum.LEFT);
        }
    }
}

