package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Floater implements LemmingBehaviour
{
    public static final Color color = Color.gray;
    private final Lemming lemming;
    private int tick; 
    public Floater(Lemming lemming)
    {
        this.lemming = lemming;
        this.tick=0;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
         g.setColor(color);
         g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
     }


    @Override
    public boolean update()
    {
        boolean blockBelow = lemming.findInferiorBlock();
        boolean frontBlock= lemming.findFrontStep();
        boolean step = frontBlock && !lemming.findSuperiorBlock();
        boolean wall =lemming.findFrontWall();
        
        updateLocation(blockBelow, wall,step,frontBlock);
        return true;

    }
    
    private void incrementTick() {
    	tick++;
    }
    private void  fall() {
    	tick=0;
    	lemming.setY(lemming.getY() + 1);
    }
    
    private void updateLocation(boolean blockBelow, boolean wall,boolean step,boolean frontBlock)
    {
    	lemming.getGame();
		boolean edgeDim = lemming.getX()== LemmingsGame.MAP_DIMENSION && lemming.getDirection()==DirectionEnum.RIGHT;;
		boolean edgeZero= lemming.getX()==0 && lemming.getDirection()==DirectionEnum.LEFT;
        
		
		
		
		if (!blockBelow)
        {
			if(tick==1) fall();
			else incrementTick();
        	
        }
        else if ((wall && frontBlock &&  !step )|| edgeZero || edgeDim)
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
        else if(step){
        	 if (lemming.getDirection() == DirectionEnum.RIGHT)
             {
        		 lemming.setY(lemming.getY() - 1);
        		 lemming.setX(lemming.getX() + 1);
             }
             else if (lemming.getDirection() == DirectionEnum.LEFT)
             {

            	 lemming.setY(lemming.getY() - 1);
        		 lemming.setX(lemming.getX() - 1);
                 
             }
        }
        else
        {
            if (lemming.getDirection() == DirectionEnum.LEFT) lemming.setX(lemming.getX() - 1);
            if (lemming.getDirection() == DirectionEnum.RIGHT) lemming.setX(lemming.getX() + 1);
        }
    }


}
    
