package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Entity.LemmingObservable;

import java.awt.*;
import java.util.ArrayList;


public class Blocker extends LemmingBehaviour
{
    public static final Color color = Color.CYAN;

    public Blocker (Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    Color getColor ()
    {
        return color;
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

    private void changeLemmingDirection (ArrayList<LemmingObservable> arrayList)
    {
        for (LemmingObservable l : arrayList)
        {
            l.changeDirectionTo((l.getX() == lemming.getX() + 1) ? DirectionEnum.RIGHT : DirectionEnum.LEFT);
        }
    }
}

