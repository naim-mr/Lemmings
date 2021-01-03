package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;


import java.awt.*;

public class Floater extends LemmingBehaviour
{	
	public static final Color color = Color.gray;
    private int tick;
    public Floater(Lemming lemming)
    {
        this.lemming = lemming;
        this.tick=0;
    }

    @Override
    Color getColor ()
    {
        return color;
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
        if (!blockBelow)
        {
			if(tick==1) fall();
			else incrementTick();
        	
        }
        else lemming.normalUpdateLocation(wall,step,frontBlock);
        
    }

}
    
