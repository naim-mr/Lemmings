package Entity.LemmingBehaviour;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Game.*;
import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;

public class Normal implements LemmingBehaviour
{
    private final Lemming lemming;


    public Normal(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
        g.setColor(Color.pink);
        g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }

    @Override
    public boolean update()
    {
		ArrayList<Block> blocks = lemming.getGame().getBlocks();

        boolean blockBelow = lemming.findInferiorBlock();
        boolean wall = lemming.findFrontBlock();

        updateLocation(blockBelow, wall);
        return true;

    }

    private void updateLocation(boolean blockBelow, boolean wall)
    {
        if (!blockBelow)
        {
            lemming.setY(lemming.getY() + 1);
        }
        else if (wall)
        {
            if (lemming.getDirection() == DirectionEnum.RIGHT)
            {

                lemming.changeDirectionTo(DirectionEnum.LEFT);
                lemming.setX(lemming.getX() - 1);
            }
            else if (lemming.getDirection() == DirectionEnum.LEFT)
            {

                lemming.setX(lemming.getX() + 1);
                lemming.changeDirectionTo(DirectionEnum.RIGHT);
            }
        }
        else
        {
            if (lemming.getDirection() == DirectionEnum.LEFT) lemming.setX(lemming.getX() - 1);
            if (lemming.getDirection() == DirectionEnum.RIGHT) lemming.setX(lemming.getX() + 1);
        }
    }


}
    

