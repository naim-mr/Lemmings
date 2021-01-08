package Entity;

import Game.LemmingsGame;

import java.util.ArrayList;

public abstract class Entity
{
    protected LemmingsGame game;
    public boolean toDelete;
    protected int x;
    protected int y;

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

    public void destroyNearbyEntities ()
    {
        ArrayList<Block> blocksToDelete = getGame().getBlocks((Block b) -> (b.getX() >= getX() - 2 && b.getX() <= getX() + 2) && (b.getY() >= getY() - 2 && b.getY() <= getY() + 2));
        ArrayList<Lemming> lemmingsToDelete = getGame().getLemmings((Lemming l) -> (l.getX() >= getX() - 2 && l.getX() <= getX() + 2) && (l.getY() >= getY() - 2 && l.getY() <= getY() + 2));

        getGame().deleteLemming(lemmingsToDelete);
        getGame().deleteBlock(blocksToDelete);
    }
}
