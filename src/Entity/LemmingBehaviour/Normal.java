package Entity.LemmingBehaviour;

import java.awt.Color;
import java.awt.Graphics;

import Game.*;
import Entity.DirectionEnum;
import Entity.Lemming;

public class Normal implements LemmingBehaviour, BehaviourRefactor
{
    private final Lemming lemming;
    public static Color color = Color.pink;

    public Normal(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
        g.setColor(color);
        g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }

    
    //En fait les leemings grimpe les mur de taille 1 mais pas deux donc j'ai modifi� �a  
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

    private void updateLocation(boolean blockBelow, boolean wall,boolean step,boolean frontBlock){
    	lemming.getGame();
    	if(!blockBelow ) {
    		   lemming.incrementFallHeight();
	        	lemming.setY(lemming.getY() + 1);
    	}
    	else {
    		normalUpdateLocation(lemming,blockBelow, wall,step,frontBlock);
    	}
    }
	    
}
    
    