package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;

public class Climber implements LemmingBehaviour,BehaviourRefactor
{	
	public static Color color = Color.lightGray;
    private final Lemming lemming;
    private boolean climbing;

    public Climber(Lemming lemming)
    {
        this.lemming = lemming;
        this.climbing = false;
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
        boolean frontBlock= lemming.findFrontStep();
        boolean wall=lemming.findFrontWall();
        boolean step=lemming.findFrontStep() && !lemming.findSuperiorBlock();
        boolean superiorBlock= lemming.findSuperiorBlock();
        
        if(blockBelow && lemming.getFallHeight()>=4) {
        	lemming.setToDelete();
        }else {
        	  if(blockBelow)lemming.resetFallHeight();
              updateLocation(blockBelow, wall,step,frontBlock,superiorBlock);
        }
        return true;
    }
    
    private void updateLocation(boolean blockBelow, boolean wall, boolean step,boolean frontBlock,boolean superiorBlock)
    {
    	lemming.getGame();
		boolean edgeDim= lemming.getX()== LemmingsGame.MAP_DIMENSION && lemming.getDirection()==DirectionEnum.RIGHT;;
		boolean edgeZero= lemming.getX()==1 && lemming.getDirection()==DirectionEnum.LEFT;
		
        if (!blockBelow && !climbing)
        {	 
        	
            lemming.setY(lemming.getY() + 1);
            lemming.incrementFallHeight();

        }else if(step && !wall ) {
        	manageStep(lemming);
        }
        else if(((!step && frontBlock))|| edgeZero || edgeDim) {
        	
        	manageFrontWall(lemming); 
        	}
        else if (wall && !climbing && frontBlock)
        {
        	climbing = true;
            lemming.setY(lemming.getY() - 1);
        }else if (wall && climbing) {
        	lemming.setY(lemming.getY() - 1);
        }
        
        else if (!wall && climbing  || (wall && superiorBlock))
        {	
        	climbing = false;
        	
        }
        else { manageNormalPace(lemming); }
    }
    
    
 
    }
