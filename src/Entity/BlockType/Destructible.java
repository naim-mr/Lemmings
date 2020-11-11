package Entity.BlockType;

import Entity.Block;
import Entity.Lemming;

import java.awt.*;
import java.util.ArrayList;

public class Destructible implements BlockType
{
    private Block block;

    // TODO destruction spawner
    public Destructible (Block block)
    {

        this.block = block;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    @Override
    public boolean setOptionalArgs (int[] args)
    {
        return false;
    }


    @Override
    public boolean destroy ()
    {
        block.setToDelete();
        return true;
    }

    @Override
    public boolean update ()
    {
        return true;
    }

}