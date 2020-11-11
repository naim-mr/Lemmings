package Entity;

import java.awt.Graphics;
import java.util.ArrayList;

import Entity.LemmingBehaviour.*;
import Game.*;

public class Lemming extends Entity
{
    private LemmingBehaviour behaviour;

    private int fallHeight;

    private DirectionEnum direction;

    public Lemming(LemmingsGame game, LemmingBehaviourEnum lemmingBehaviour, int x, int y)
    {
        this.game = game;
        changeBehaviourTo(lemmingBehaviour);
        this.direction = DirectionEnum.RIGHT;
        this.x = x;
        this.y = y;
        int fallHeight=0;
          
        this.toDelete = false;
    }

    @Override
    public void draw(Graphics g, int x, int y)
    {
        behaviour.draw(g, x, y);
    }

    @Override
    public boolean update ()
    {
        return behaviour.update();
    }

    
    public int  getFallHeight() {
    	return this.fallHeight;
    }
    
    public void incrementFallHeight() {
    	this.fallHeight++;
    }
    public void resetFallHeight() {
    	this.fallHeight=0;
    }

    
    public void changeBehaviourTo(LemmingBehaviourEnum lemmingBehaviour)
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
            case CARPENTER:
                behaviour = new Carpenter(this);
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
    public boolean findSuperiorBlock()
    {
        return getGame().getBlocks((Block b) -> b.getY() == getY() - 1 && b.getX() == getX()).size() >= 1;
    }

    // TODO : refactoriser, modifier toutes les méthodes & listes pour qu'elles ne renvoient jamais null
    public boolean findInferiorBlock()
    {
        return getGame().getBlocks((Block b) -> b.getY() == getY() + 1 && b.getX() == getX()).size() >= 1;
    }

    public Block getInferiorBlock()
    {
        ArrayList<Block> list = getGame().getBlocks((Block b) -> b.getY() == getY() + 1 && b.getX() == getX());

        return list.size() >= 1 ? list.get(0) : null;
    }

    public Block getFrontBlock ()
    {
        ArrayList<Block> list;
        if (getDirection() == DirectionEnum.RIGHT) list = getGame().getBlocks((Block b) -> b.getX() == getX() + 1 && b.getY() == getY());
        else list = getGame().getBlocks((Block b) -> b.getX() == getX() - 1 && b.getY() == getY() );

        return list.size() >= 1 ? list.get(0) : null;
    }
    
    // pour diff�rencier les murs et les marches je retroune la taille de la liste
    public boolean findFrontStep()
    {
        ArrayList<Block> list;
        if (getDirection() == DirectionEnum.RIGHT) list = getGame().getBlocks((Block b) -> (b.getX() == getX() + 1 && b.getY() == getY()) || getX() + 1 == LemmingsGame.MAP_DIMENSION);
        else list = getGame().getBlocks((Block b) -> ((b.getX() + 1 == getX() && b.getY() == getY()) || getX() == 0));

        return list.size()==1; 
    }
    
    public boolean findFrontWall()
    {
        ArrayList<Block> list;
        getGame().getBlocks((Block b) -> (b.getX() == getX() + 1) && (b.getY() == getY() || b.getY() == getY() - 1));
        if (getDirection() == DirectionEnum.RIGHT) list = getGame().getBlocks((Block b) -> (b.getX() == getX() + 1) && (b.getY() == getY() || b.getY() == getY() - 1));
        else list = getGame().getBlocks((Block b) -> (b.getX() == getX() - 1) && (b.getY() == getY() || b.getY() == getY() - 1));
         
        return list.size()>1;
    }

    public ArrayList<Lemming> getSideLemmings ()
    {
        return getGame().getLemmings((Lemming l) -> (l.getX() == getX() + 1 || l.getX() == getX() - 1) && l.getY() == getY());
    }

}