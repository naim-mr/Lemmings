package Entity.BlockType;

import java.awt.Graphics;

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

    }

    @Override
    public void update()
    {

    }
}
