package Entity;

import Game.LemmingsGame;

import java.awt.*;

public abstract class Entity
{
    protected LemmingsGame game;
    protected Color color;
    protected boolean toDelete;
    protected int x;
    protected int y;
    protected int width; // normalement inutile
    protected int height; // normalement inutile

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

}
