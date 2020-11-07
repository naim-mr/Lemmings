package Entity.LemmingBehaviour;

import Entity.*;
import Entity.Lemming;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

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

    // todo refactor
    @Override
    public boolean update(ArrayList<Block> blocks, ArrayList<Lemming> lemmings)
    {
        boolean blockUpdated = false;
        Block blockBelow = null;
        int i = 0;

        while (i < blocks.size() && blockBelow == null)
        {
            //On cherche s'il y a un block en dessous
            if (blocks.get(i).getY() == lemming.getY() + 1 && blocks.get(i).getX() == lemming.getX())
            {
                blockBelow = blocks.get(i);

            }
            i++;
        }

        if (blockBelow != null)
            blockUpdated = blockBelow.update(blocks, lemmings);// s'il oui , on update le block -> ON DIG DOUG DIGON
        if (blockUpdated)
        {
            //Si le block c'est update donc s'il est destructible on incr�mente et descend
            blockDigged++;
            lemming.setY(lemming.getY() + 1);
        }
        if (blockDigged == 4 || !blockUpdated)
        {
            // on s'arrete au bout de 5 ou s'il y a rien
            lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);

        }
        if (blockBelow == null && blockDigged > 0)
            lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);// euh je dois revoir la n�cessit� de cette ligne


        return true;
    }
}
