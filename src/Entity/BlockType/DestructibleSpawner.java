package Entity.BlockType;

import Entity.Block;

import java.util.ArrayList;

public class DestructibleSpawner extends BlockType
{
    private ArrayList<Block> blocksToSpawn;
    public DestructibleSpawner (Block block)
    {
        this.block = block;
    }

    @Override
    public void setBlocksToSpawn (ArrayList<Block> blocks)
    {
        blocksToSpawn = blocks;
    }

    @Override
    public boolean destroy ()
    {
        block.setToDelete();
        block.getGame().createBlock(blocksToSpawn);
        return true;
    }
}
