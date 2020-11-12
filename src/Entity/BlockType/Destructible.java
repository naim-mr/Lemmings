package Entity.BlockType;

import Entity.Block;

import java.awt.*;

public class Destructible implements BlockType
{
    private final Block block;
    public static final Color color = Color.GREEN;

    public Destructible (Block block)
    {
        this.block = block;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(color);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    @Override
    public boolean destroy ()
    {
        block.setToDelete();
        return true;
    }
}