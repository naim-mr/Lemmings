package Entity;

import java.awt.Graphics;
import java.util.ArrayList;

import Entity.LemmingBehaviour.*;
import Game.*;

public class Lemming extends Entity
{
    private LemmingBehaviour behaviour;

    public static boolean[] life = {true, true, true}; // ??

    private DirectionEnum direction;

    public Lemming(LemmingsGame game, LemmingBehaviourEnum lemmingBehaviour, int x, int y)
    {
        this.game = game;
        changeBehaviourTo(lemmingBehaviour);
        this.direction = DirectionEnum.RIGHT;
        this.x = x;
        this.y = y;
        this.width = 25;
        this.height = 35;
        this.toDelete = false;
    }


    @Override
    public void draw(Graphics g, int x, int y)
    {
        behaviour.draw(g, x, y);
    }

    @Override
    public boolean update(ArrayList<Block> blocks, ArrayList<Lemming> lemmings)
    {
        //System.out.println("lemming"+direction);
        return behaviour.update(blocks, lemmings);

    }

    public void changeBehaviourTo(LemmingBehaviourEnum lemmingBehaviour)
    {
        DirectionEnum pastDirection = this.getDirection();
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

    public void changeDirectionTo(DirectionEnum direction)
    {
        this.direction = direction;
    }

    public DirectionEnum getDirection()
    {
        return this.direction;
    }

    public LemmingsGame getGame()
    {
        return game;
    }

    public boolean findInferiorBlock()
    {
        return getGame().getBlocks((Block b) -> b.getY() == getY() + 1 && b.getX() == getX()).size() >= 1;
    }

    public Block getFrontBlock ()
    {
        if (getDirection() == DirectionEnum.RIGHT)
        {
            return getGame().getBlocks((Block b) -> b.getX() == getX() + 1 && b.getY() == getY()).get(0);
        }
        else return getGame().getBlocks((Block b) -> b.getX() == getX() - 1 && b.getY() == getY()).get(0);
    }

}