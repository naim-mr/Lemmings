package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Basher implements LemmingBehaviour
{	
	public static final Color color = Color.red;
    private final Lemming lemming;
    private int blockDrill;
    public Basher(Lemming lemming)
    {
        this.blockDrill = 0;
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
        g.setColor(color);
        g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }

    @Override
    public boolean update ()
    {
        boolean blockBelow = lemming.findInferiorBlock();
        Block blockForward = lemming.getFrontBlock();
        boolean blockUpdated = false;
        if (blockBelow) { 
        	blockUpdated = lemming.getGame().deleteBlock(blockForward);
        	lemming.resetFallHeight();
        }
        updateLocation(blockUpdated, blockBelow, blockForward);
        if(blockBelow && lemming.getFallHeight()>=4) {
        	lemming.setToDelete();
        }
        
        return true;
    }

    private void updateLocation(boolean blockUpdated, boolean blockBelow, Block blockForward)
    {	
    	if (!blockBelow) {
    		lemming.setY(lemming.getY() + 1);
    		lemming.incrementFallHeight();
    	}
        else if (blockUpdated)
        {	
        	
            blockDrill++;
            updateHorizontalLocation();
            blockForward=null;;
        }
        else if (blockForward == null) updateHorizontalLocation();
        if(!blockUpdated && blockForward!=null|| blockDrill==4) lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
        	
                
    }

    private void updateHorizontalLocation()
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
        else if (lemming.getDirection() == DirectionEnum.LEFT) lemming.setX(lemming.getX() - 1);
        else if (lemming.getDirection() == DirectionEnum.RIGHT) lemming.setX(lemming.getX() + 1);
    }

}
