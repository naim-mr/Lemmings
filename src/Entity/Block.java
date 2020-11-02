package Entity;

import Entity.BlockType.BlockType;

import java.awt.*;

public class Block extends Entity
{
    private final BlockType blockType;

    // TODO : Factory method.
    public Block(BlockType blockType, int x, int y, int width, int height)
    {
        this.blockType = blockType;
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        blockType.draw(graphics, windowX, windowY);
    }
}
