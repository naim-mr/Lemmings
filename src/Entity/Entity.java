package Entity;

import Game.LemmingsGame;

public abstract class Entity
{
    protected LemmingsGame game;
    protected boolean toDelete;
    protected int x;
    protected int y;

    public void setToDelete()
    {
        toDelete = true;
    }

    public boolean getToDelete()
    {
        return this.toDelete;
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
