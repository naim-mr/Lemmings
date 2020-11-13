package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Bomber implements LemmingBehaviour
{
    public static final Color color = Color.magenta;
    private final Lemming lemming;
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

    @Override
    public void draw (Graphics g, int windowX, int windowY)
    {
        g.setColor(color);
        g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);

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
        boolean edgeDim = lemming.getX() == LemmingsGame.MAP_DIMENSION && lemming.getDirection() == DirectionEnum.RIGHT;
        boolean edgeZero = lemming.getX() == 0 && lemming.getDirection() == DirectionEnum.LEFT;

        if (!blockBelow)
        {
            lemming.incrementFallHeight();
            lemming.setY(lemming.getY() + 1);
        }
        else lemming.normalUpdateLocation(wall, step, frontBlock);

    }


}
    

