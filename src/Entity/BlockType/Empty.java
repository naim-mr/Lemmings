package Entity.BlockType;

import java.awt.Graphics;
import java.util.ArrayList;

import Entity.Block;
import Entity.Lemming;

public class Empty implements BlockType
{
    private final Block block;

    public Empty(Block block)
    {
        this.block = block;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {}

    @Override
    public boolean setOptionalArgs (int[] args)
    {
        return false;
    }

    @Override
    public boolean update ()
    {
    	return true;
    }

    @Override
    public boolean destroy()
    {
        return false;
    }
}
