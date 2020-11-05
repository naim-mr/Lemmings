package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Entity.LemmingBehaviour.*;
import Game.*;

public class Lemming extends Entity
{
    public LemmingBehaviour behaviour;

    public static boolean[] life = {true, true, true}; // ??

    public Lemming(LemmingBehaviourEnum lemmingBehaviour, int x, int y)
    {
        changeBehaviourTo(lemmingBehaviour);
        this.x = x;
        this.y = y;
        this.width = 25;
        this.height = 35;
    }

    // Cette méthode ne sert actuellement plus, certains lemmings ont un comportement différent qu'il faudra adapter. Le lambda calcul nous servira là certainement.
    public boolean update(ArrayList<Block> blocks)
    {
        Block blockBelow = null;
        for (Block b : blocks)
        {

            int[] blockCoord = LemmingsGameView.mapToWindowCoords(b.getX(), b.getY());
            int[] lemmingCoord = LemmingsGameView.mapToWindowCoords(x, y);
            if (b.getX() == this.getX() + this.height)
            {

                blockBelow = b;
            }
        }

        if (blockBelow != null)
        {

            this.y++;
            if (life[0] || life[1])
            {
                life[0] = true;
                life[1] = true;
                life[2] = true;
                return true;
            }
            return true;
        }
        else
        {
            if (life[0]) life[0] = false;
            else if (life[1]) life[1] = false;
            else life[2] = false;
            this.x++;
            return true;
        }
    }

    @Override
    public void draw(Graphics g, int x, int y)
    {
        g.setColor(Color.black);
        g.fillRect(x, y, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE); // Chaque Lemming sera dessiné de manière différente, donc il faudrait faire un draw délégué, à la manière du update();
    }

    @Override
    public void update()
    {
        behaviour.update();
    }

    public void changeBehaviourTo (LemmingBehaviourEnum lemmingBehaviour)
    {
        switch (lemmingBehaviour)
        {
            case BASHER:
                behaviour = new Basher(this);
                break;
            case BLOCKER:
                behaviour = new Blocker(this);
                break;
            case BOMBER:
                behaviour = new Bomber(this);
                break;
            case CLIMBER:
                behaviour = new Climber(this);
                break;
            case DIGGER:
                behaviour = new Digger(this);
                break;
            case FLOATER:
                behaviour = new Floater(this);
                break;
            case NORMAL:
                behaviour = new Normal(this);
                break;
        }
    }
}
