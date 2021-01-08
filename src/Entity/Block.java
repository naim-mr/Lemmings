package Entity;

import Entity.BlockType.*;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Block extends Entity
{
    private BlockType blockType;
    public static final int width = LemmingsGameView.TILE_SIZE; // normalement inutile
    public static final int height = LemmingsGameView.TILE_SIZE; // normalement inutile
    public Block (LemmingsGame game, BlockTypeEnum blockType, int x, int y)
    {
        changeTypeTo(blockType);
        this.game = game;
        this.coord=new Coordinate (x,y);
        this.toDelete = false;
       
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        blockType.draw(graphics, windowX, windowY);
    }

    private void changeTypeTo (BlockTypeEnum blockTypeEnum)
    {
        switch (blockTypeEnum)
        {
            case DESTRUCTIBLE_BLOCK:
                blockType = new Destructible(this);
                break;
            case DESTRUCTIBLE_BLOCK_SPAWNER:
                blockType = new DestructibleSpawner(this);
                break;
            case DESTRUCTIBLE_BLOCK_LANDMINE:
                blockType = new DestructibleLandmine(this);
                break;
            case EXIT_BLOCK:
                blockType = new Exit(this);
                break;
            case INDESTRUCTIBLE_BLOCK:
                blockType = new Indestructible(this);
                break;
            case LAVA_BLOCK:
                blockType = new Lava(this);
                break;
            case SPAWNER_BLOCK:
                blockType = new Spawner(this);
                break;
            case TELEPORTER_BLOCK:
                blockType = new Teleporter(this);
                break;
        }
    }

    @Override
    public boolean update ()
    {
        return blockType.update();
    }

    public boolean destroy ()
    {
        return blockType.destroy();
    }

    public ArrayList<LemmingObservable> getLemmingsOnBlock ()
    {
        return getGame().getLemmings((LemmingObservable l) -> l.getX() == getX() && l.getY() == getY() - 1);
    }

    public void setOptionalArgs (int... args)
    {
        blockType.setOptionalArgs(args);
    }

    public void setOptionalArgs (ArrayList<BlockObservable> blocks)
    {
        blockType.setOptionalArgs(blocks);
    }


}
