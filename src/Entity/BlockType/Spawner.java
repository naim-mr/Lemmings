package Entity.BlockType;

import java.awt.Color;

import Entity.Block;
import Entity.Lemming;

import java.awt.Graphics;
import java.util.ArrayList;

public class Spawner implements BlockType
{
    private final Block block;
    private int lemmingsNb;

    public Spawner(Block block)
    {
        this.block = block;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    @Override
    public boolean setOptionalArgs (int[] args)
    {
        lemmingsNb = args[0];
        return true;
    }

    @Override
    public boolean update ()
    {
        // block.getGame().
    	return true;
    }

    @Override
    public boolean destroy()
    {
        return false;
    }


}
