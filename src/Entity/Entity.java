package Entity;

import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;

public abstract class Entity
{
    protected LemmingsGame game;
    protected Color color;
    protected boolean toDelete;
    protected int x;
    protected int y;
    public static final int width = LemmingsGameView.TILE_SIZE; // normalement inutile
    public static final int height = LemmingsGameView.TILE_SIZE; // normalement inutile


    public int getWidth()
    {
        return width;
    }

    public void setToDelete()
    {
        toDelete = true;
    }

    public boolean getToDelete()
    {
        return this.toDelete;
    }

    public int getHeight()
    {
        return height;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public abstract void draw(Graphics graphics, int windowX, int windowY);

    public abstract boolean update();

    public LemmingsGame getGame()
    {
        return game;
    }

    public boolean findSuperiorBlock ()
    {
        return getGame().getBlocks((Block b) -> b.getY() == getY() - 1 && b.getX() == getX()).size() >= 1;
    }

}
