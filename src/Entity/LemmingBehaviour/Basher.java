package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.BlockObservable;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;

import java.awt.*;

public class Basher extends LemmingBehaviour
{
    public static final Color color = Color.red;
    private int blockDrill;

    public Basher (Lemming lemming)
    {
        this.blockDrill = 0;
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
        boolean blockBelow = lemming.findInferiorBlock();
        BlockObservable blockForward = lemming.getFrontBlock();
        boolean blockUpdated = false;
        if (blockBelow)
        {
            blockUpdated = lemming.getGame().deleteBlock(blockForward);
            lemming.resetFallHeight();
        }
        updateLocation(blockUpdated, blockBelow, blockForward);
        if (blockBelow && lemming.getFallHeight() >= 4)
        {
            lemming.setToDelete();
        }

        return true;
    }

    private void updateLocation (boolean blockUpdated, boolean blockBelow, BlockObservable blockForward)
    {
        if (!blockBelow)
        {
            lemming.setY(lemming.getY() + 1);
            lemming.incrementFallHeight();
        }
        else if (blockUpdated)
        {

            blockDrill++;
            updateHorizontalLocation();
            blockForward = null;
        }
        else if (blockForward == null) updateHorizontalLocation();
        if (!blockUpdated && blockForward != null || blockDrill == 4)
            lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);


    }

    private void updateHorizontalLocation ()
    {
        if (lemming.getDirection() == DirectionEnum.LEFT && lemming.getX() == 0)
        {
            lemming.setX(lemming.getX() + 1);
            lemming.changeDirectionTo(DirectionEnum.RIGHT);
        }
        else if (lemming.getDirection() == DirectionEnum.RIGHT && lemming.getX() == LemmingsGame.MAP_DIMENSION)
        {
            lemming.setX(lemming.getX() - 1);
            lemming.changeDirectionTo(DirectionEnum.LEFT);
        }
        else  lemming.setX(lemming.getX() + lemming.getDirection().getX());
      
    }

}
