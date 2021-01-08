package Entity.LemmingBehaviour;

import Entity.Lemming;

public class Normal extends LemmingBehaviour
{
    public Normal (Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public boolean update ()
    {
        boolean blockBelow = lemming.findInferiorBlock();
        boolean frontBlock = lemming.findFrontStep();
        boolean step = frontBlock && !lemming.findSuperiorBlock();
        boolean wall = lemming.findFrontWall();
        if (blockBelow && lemming.getFallHeight() >= 4)
        {
            lemming.setToDelete();
        }
        else
        {
            if (blockBelow) lemming.resetFallHeight();
            updateLocation(blockBelow, wall, step, frontBlock);
        }

        return true;

    }

    private void updateLocation (boolean blockBelow, boolean wall, boolean step, boolean frontBlock)
    {
        if (!blockBelow)
        {
            lemming.incrementFallHeight();
            lemming.setY(lemming.getY() + 1);
        }
        else
        {
            lemming.normalUpdateLocation(wall, step, frontBlock);
        }
    }

}
    
    