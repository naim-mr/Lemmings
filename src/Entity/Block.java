package Entity;

import Entity.BlockType.*;
import Game.LemmingsGame;
import java.util.ArrayList;

public class Block extends Entity
{
    private BlockTypeEnum blockTypeEnum;
    private BlockType blockType;

    public Block (LemmingsGame game, BlockTypeEnum blockType, int x, int y)
    {
        changeTypeTo(blockType);
        this.game = game;
        this.x = x;
        this.toDelete = false;
        this.y = y;
    }

    private void changeTypeTo (BlockTypeEnum blockTypeEnum)
    {
        this.blockTypeEnum = blockTypeEnum;
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

    public ArrayList<Lemming> getLemmingsOnBlock ()
    {
        return getGame().getLemmings((Lemming l) -> l.getX() == getX() && l.getY() == getY() - 1);
    }

    public BlockTypeEnum getType()
    {
        return blockTypeEnum;
    }

    public void setNumberToSpawn (int nb)
    {
        blockType.setNumberToSpawn(nb);
    }
    public void setBlocksToSpawn (ArrayList<Block> blocks)
    {
        blockType.setBlocksToSpawn(blocks);
    }
    public void setTeleportTo (int x, int y)
    {
        blockType.setTeleportTo(x, y);
    }
}
