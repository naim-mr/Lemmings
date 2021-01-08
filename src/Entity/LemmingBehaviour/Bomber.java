package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Bomber extends LemmingBehaviour
{
    private int countDown;

    public Bomber (Lemming lemming)
    {
        this.lemming = lemming;
        countDown = 3;
    }

    private void decrementCountDown ()
    {
        this.countDown--;
    }

    private void blast ()
    {
        ArrayList<Block> blocksToDelete = lemming.getGame().getBlocks((Block b) -> (b.getX() >= lemming.getX() - 2 && b.getX() <= lemming.getX() + 2) && (b.getY() >= lemming.getY() - 2 && b.getY() <= lemming.getY() + 2));
        ArrayList<Lemming> lemmingsToDelete = lemming.getGame().getLemmings((Lemming l) -> (l.getX() >= lemming.getX() - 2 && l.getX() <= lemming.getX() + 2) && (l.getY() >= lemming.getY() - 2 && l.getY() <= lemming.getY() + 2));

        lemmingsToDelete.add(this.lemming);
        lemming.getGame().deleteLemming(lemmingsToDelete);
        lemming.getGame().deleteBlock(blocksToDelete);
    }

    @Override
    public boolean update ()
    {

        if (countDown == 0)
        {
            blast();
        }
        else
        {
            boolean blockBelow = lemming.findInferiorBlock();
            boolean frontBlock = lemming.findFrontStep();
            boolean step = frontBlock && !lemming.findSuperiorBlock();
            boolean wall = lemming.findFrontWall();
            if (blockBelow && lemming.getFallHeight() >= 4)
            {
                lemming.setToDelete();
            }
            else
            {
                if (blockBelow) lemming.resetFallHeight();
                updateLocation(blockBelow, wall, step, frontBlock);
                decrementCountDown();
            }
        }
        return true;

    }

    private void updateLocation (boolean blockBelow, boolean wall, boolean step, boolean frontBlock)
    {
        if (!blockBelow)
        {
            lemming.incrementFallHeight();
            lemming.setY(lemming.getY() + 1);
        }
        else lemming.normalUpdateLocation(wall, step, frontBlock);
    }
}
    

