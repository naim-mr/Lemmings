package Entity.BlockType;

import Entity.Block;

import java.awt.*;
import java.util.ArrayList;

public class DestructibleSpawner implements BlockType
{
    private Block block;
    private ArrayList<Block> blocksToSpawn;

    public DestructibleSpawner (Block block)
    {
        this.block = block;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {

    }

    @Override
    public boolean setOptionalArgs (int[] args)
    {
        return false;
    }

    @Override
    public boolean setOptionalArgs (ArrayList<Block> blocks)
    {
        blocksToSpawn = blocks;
        return true;
    }

    @Override
    public boolean destroy ()
    {
        block.setToDelete();
        block.getGame().createBlock(blocksToSpawn);
        return true;
    }

}
