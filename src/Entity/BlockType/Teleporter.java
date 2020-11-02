package Entity.BlockType;

import java.awt.Graphics;

import Entity.Block;

public class Teleporter implements BlockType
{
    private final Block block;

    public Teleporter(Block block)
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
