package Entity.LemmingBehaviour;

import Entity.*;
import Entity.Lemming;
import Game.LemmingsGameView;

import java.awt.*;

public class Digger implements LemmingBehaviour
{
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
        g.setColor(Color.yellow);
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
        if (blockDigged == 4 || !blockUpdated)
        {
            // on s'arrete au bout de 5 ou s'il y a rien
            lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);

        }
        if (blockBelow == null && blockDigged > 0)
            lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);// euh je dois revoir la n�cessit� de cette ligne
    }

    public void updateLocation(boolean blockUpdated)
    {
        if (blockUpdated)
        {
            //Si le block c'est update donc s'il est destructible on incr�mente et descend
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
