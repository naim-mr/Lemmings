package Entity.LemmingBehaviour;

import Entity.Lemming;

public class Floater extends LemmingBehaviour
{	
    private int tick;
    public Floater(Lemming lemming)
    {
        this.lemming = lemming;
        this.tick=0;
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
    
