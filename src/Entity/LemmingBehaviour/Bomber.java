package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.BlockObservable;
import Entity.DirectionEnum;
import Entity.Lemming;
import Entity.LemmingObservable;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Bomber extends LemmingBehaviour
{
    public static final Color color = Color.magenta;
    private int countDown;

    public Bomber (Lemming lemming)
    {
        this.lemming = lemming;
        countDown = 3;
    }

    @Override
    Color getColor ()
    {
        return color;
    }

    private void decrementCountDown ()
    {
        this.countDown--;
    }

    private void blast ()
    {
        ArrayList<BlockObservable> blocksToDelete = lemming.getGame().getBlocks((BlockObservable b) -> (b.getX() >= lemming.getX() - 2 && b.getX() <= lemming.getX() + 2) && (b.getY() >= lemming.getY() - 2 && b.getY() <= lemming.getY() + 2));
        ArrayList<LemmingObservable> lemmingsToDelete = lemming.getGame().getLemmings((LemmingObservable l) -> (l.getX() >= lemming.getX() - 2 && l.getX() <= lemming.getX() + 2) && (l.getY() >= lemming.getY() - 2 && l.getY() <= lemming.getY() + 2));

        lemmingsToDelete.add(new LemmingObservable(this.lemming));
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
    

