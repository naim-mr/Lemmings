package Entity.BlockType;

import Entity.Block;

import java.awt.*;

public class Destructible extends BlockType
{
    public Destructible (Block block)
    {
        this.block = block;
    }

    @Override
    public boolean destroy ()
    {
        block.setToDelete();
        return true;
    }
}