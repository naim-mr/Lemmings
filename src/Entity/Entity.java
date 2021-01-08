package Entity;

import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;

public abstract class Entity implements IEntity
{
    protected LemmingsGame game;
    protected Color color;
    protected boolean toDelete;
    protected Coordinate coord ;
  
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
        return coord.getX();
    }

    public int getY()
    {
        return coord.getY();
    }

    public void setX(int x)
    {
    	coord.setX(x);
    }

    public void setY(int y)
    {
        coord.setY(y);
    }

    public abstract void draw(Graphics graphics, int windowX, int windowY);

    public abstract boolean update();

    public LemmingsGame getGame()
    {
        return game;
    }

    public boolean findSuperiorBlock ()
    {
        return getGame().getBlocks((BlockObservable b) -> b.getY() == getY() - 1 && b.getX() == getX()).size() >= 1;
    }

}
