package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;

public class Climber implements LemmingBehaviour,BehaviourRefactor
{
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
        g.setColor(Color.cyan);
        g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }


    @Override
    public boolean update ()
    {	
    	
        boolean blockBelow = lemming.findInferiorBlock();
        
        boolean frontBlock= lemming.findFrontStep();
        
        boolean wall=lemming.findFrontWall();
        boolean step=lemming.findFrontStep() && !lemming.findSuperiorBlock();
        
        updateLocation(blockBelow, wall,step,frontBlock);
        return true;
    }
    
    private void updateLocation(boolean blockBelow, boolean wall, boolean step,boolean frontBlock)
    {
    	lemming.getGame();
		boolean edgeDim = lemming.getX()== LemmingsGame.MAP_DIMENSION && lemming.getDirection()==DirectionEnum.RIGHT;;
		boolean edgeZero= lemming.getX()==0 && lemming.getDirection()==DirectionEnum.LEFT;
    
        if (!blockBelow && !climbing)
        {
            lemming.setY(lemming.getY() + 1);

        }else if(step && !wall) {
        	manageStep(lemming);
        }
        else if((wall && !step && frontBlock) || edgeZero || edgeDim ) {  manageFrontWall(lemming); }
        else if (wall && !climbing && step)
        {
        	climbing = true;
            lemming.setY(lemming.getY() - 1);
        }
        else if(wall && climbing && frontBlock ) {
        	lemming.setY(lemming.getY() - 1);
        }
        else if (!wall && climbing)
        {
        	climbing = false;
        	manageNormalPace(lemming);
        }
        else { manageNormalPace(lemming); }
    }
    
    
 
    }
