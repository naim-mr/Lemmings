package Entity.LemmingBehaviour;

import Entity.*;
import Game.LemmingsGameView;

import java.awt.*;

public class Digger implements LemmingBehaviour
{	
	public static final Color color = Color.yellow;
    private final Lemming lemming;
    private int blockDigged;

    public Digger(Lemming lemming)
    {
        this.lemming = lemming;


        blockDigged = 0;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
        g.setColor(color);
        g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE); // Chaque Lemming sera dessiné de manière différente, donc il faudrait faire un draw délégué, à la manière du update();

    }

    @Override
    public boolean update ()
    {
        boolean blockUpdated = false;
        Block blockBelow = lemming.getInferiorBlock();
        if (blockBelow != null) blockUpdated = destroyBlock(blockBelow);
        updateLocation(blockUpdated);
        updateBehaviour(blockUpdated, blockBelow);
        return true;
    }

    private void updateBehaviour(boolean blockUpdated, Block blockBelow)
    {
        if (blockDigged == 5 || !blockUpdated) lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
        if (blockBelow == null && blockDigged > 0) lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
    
    }

    public void updateLocation(boolean blockUpdated)
    {
        if (blockUpdated)
        {
            blockDigged++;
            lemming.setY(lemming.getY() + 1);
        }
    }

    public boolean destroyBlock (Block blockBelow)
    {
        if (blockBelow != null) return lemming.getGame().deleteBlock(blockBelow);
        else return false;
    }
}
