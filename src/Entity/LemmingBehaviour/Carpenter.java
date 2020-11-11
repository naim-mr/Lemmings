package Entity.LemmingBehaviour;

import Entity.BlockType.BlockTypeEnum;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGameView;

import java.awt.*;

public class Carpenter implements LemmingBehaviour
{
    public static Color color = new Color(160, 82, 45);
    private Lemming lemming;
    private int leftStairs = 5;
    private CarpenterStatus status = CarpenterStatus.BUILDING;

    public Carpenter (Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(color);
        graphics.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }

    @Override
    public boolean update ()
    {
        if (status == CarpenterStatus.BUILDING)
        {
           if (lemming.findInferiorBlock()) placeStaircase();
           else lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
        }
        else
        {
            climbStaircase();
        }
        return false;
    }

    private void placeStaircase ()
    {
        int direction = (lemming.getDirection() == DirectionEnum.LEFT) ? - 1 : 1;
        if (!lemming.findFrontStep() && leftStairs > 0)
        {
            lemming.getGame().createBlock(BlockTypeEnum.DESTRUCTIBLE_BLOCK, lemming.getX() + direction, lemming.getY());
            --leftStairs;
            status = CarpenterStatus.CLIMBING;
        }
        else lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
    }

    private void climbStaircase ()
    {
        int direction = (lemming.getDirection() == DirectionEnum.LEFT) ? - 1 : 1;
        if (lemming.findFrontWall() || lemming.findSuperiorBlock()) lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
        else
        {
            lemming.setX(lemming.getX() + direction);
            lemming.setY(lemming.getY() - 1);
            status = CarpenterStatus.BUILDING;
        }
    }
}

enum CarpenterStatus
{
    BUILDING,
    CLIMBING
}