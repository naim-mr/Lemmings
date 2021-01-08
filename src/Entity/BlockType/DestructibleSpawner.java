package Entity.BlockType;

import Entity.Block;
import Entity.BlockObservable;

import java.awt.*;
import java.util.ArrayList;

public class DestructibleSpawner extends BlockType
{
    private ArrayList<BlockObservable> blocksToSpawn;
    public static final Color color = Color.GREEN;

    public DestructibleSpawner (Block block)
    {
        this.block = block;
    }

    @Override
    public Color getColor ()
    {
        return color;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        super.draw(graphics, windowX, windowY);
        graphics.setColor(Color.BLACK);
        graphics.drawString("S", windowX + 8, windowY + 13);
    }

    @Override
    public void setOptionalArgs (ArrayList<BlockObservable> blocks)
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
