package Entity.BlockType;

import Entity.Block;

import java.awt.*;

public class Destructible extends BlockType
{
    public static final Color color = Color.GREEN;

    public Destructible (Block block)
    {
        this.block = block;
    }

    @Override
    public Color getColor ()
    {
        return color;
    }

    @Override
    public boolean destroy ()
    {
        block.setToDelete();
        return true;
    }
}