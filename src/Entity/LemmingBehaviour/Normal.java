package Entity.LemmingBehaviour;

import java.awt.Color;
import java.awt.Graphics;

import Game.*;
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

    
    //En fait les leemings grimpe les mur de taille 1 mais pas deux donc j'ai modifié ça  
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

    
    
    
    private void updateLocation(boolean blockBelow, boolean wall,boolean step,boolean frontBlock)
    {
    	lemming.getGame();
		boolean edgeDim = lemming.getX()== LemmingsGame.MAP_DIMENSION && lemming.getDirection()==DirectionEnum.RIGHT;;
		boolean edgeZero= lemming.getX()==0 && lemming.getDirection()==DirectionEnum.LEFT;
        
		if(blockBelow ) {
			if(lemming.getFallHeight() <4) lemming.resetFallHeight();
		}
		
		
		
		if (!blockBelow)
        {
            lemming.incrementFallHeight();
        	lemming.setY(lemming.getY() + 1);
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
    

