package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;


public class Blocker implements LemmingBehaviour
{
    public static final Color color = Color.CYAN;
    private final Lemming lemming;

    public Blocker (Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(Color.CYAN);
        graphics.setColor(color);
        graphics.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
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
            l.changeDirectionTo((l.getDirection() == DirectionEnum.LEFT) ? DirectionEnum.RIGHT : DirectionEnum.LEFT);
        }
    }
}

