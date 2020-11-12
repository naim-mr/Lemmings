package Entity.BlockType;

import Entity.Block;

import java.awt.*;
import java.util.ArrayList;

public class DestructibleSpawner implements BlockType
{
    private final Block block;
    private ArrayList<Block> blocksToSpawn;
    public static final Color color = Color.GREEN;

    public DestructibleSpawner (Block block)
    {
        this.block = block;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(color);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawString("S", windowX + 8, windowY + 13);
    }

    @Override
    public void setOptionalArgs (ArrayList<Block> blocks)
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
