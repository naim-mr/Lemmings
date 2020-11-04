package Entity.BlockType;

import java.awt.*;

import Entity.Block;

public class Exit implements BlockType
{
    private final Block block;

    public Exit(Block block)
    {
        this.block = block;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    @Override
    public void update()
    {

    }
}
