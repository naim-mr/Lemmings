package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;

import java.awt.*;

public class Climber extends LemmingBehaviour
{
    public static final Color color = Color.lightGray;
    private boolean climbing;

    public Climber (Lemming lemming)
    {
        this.lemming = lemming;
        this.climbing = false;
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
        boolean frontBlock = lemming.findFrontStep();
        boolean wall = lemming.findFrontWall();
        boolean step = lemming.findFrontStep() && !lemming.findSuperiorBlock();
        boolean superiorBlock = lemming.findSuperiorBlock();

        if (blockBelow && lemming.getFallHeight() >= 4)
        {
            lemming.setToDelete();
        }
        else
        {
            if (blockBelow) lemming.resetFallHeight();
            updateLocation(blockBelow, wall, step, frontBlock, superiorBlock);
        }
        return true;
    }

    private void updateLocation (boolean blockBelow, boolean wall, boolean step, boolean frontBlock, boolean superiorBlock)
    {
        boolean edgeDim = lemming.getX() == LemmingsGame.MAP_DIMENSION && lemming.getDirection() == DirectionEnum.RIGHT;
        boolean edgeZero = lemming.getX() == 0 && lemming.getDirection() == DirectionEnum.LEFT;

        if (!blockBelow && !climbing)
        {
            lemming.setY(lemming.getY() + 1);
            lemming.incrementFallHeight();

        }
        else if (step && !wall)
        {
            lemming.manageStep();
        }
        else if (((!step && frontBlock)) || edgeZero || edgeDim)
        {
            lemming.manageFrontWall();
        }
        else if (wall && !climbing && frontBlock)
        {
            climbing = true;
            lemming.setY(lemming.getY() - 1);
        }
        else if (wall && climbing)
        {
            lemming.setY(lemming.getY() - 1);
        }

        else if (!wall && climbing || (wall && superiorBlock))
        {
            climbing = false;

        }
        else
        {
            lemming.manageNormalPace();
        }
    }
}
