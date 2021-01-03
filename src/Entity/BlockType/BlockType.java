package Entity.BlockType;

import java.awt.*;
import java.util.ArrayList;

import Entity.*;

public abstract class BlockType
{
    protected Block block;

    public abstract Color getColor();

    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(getColor());
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }
    public void setOptionalArgs (int[] args){}
    public void setOptionalArgs (ArrayList<Block> blocks) {
    }
    public boolean update ()
    {
        return false;
    }
    public boolean destroy ()
    {
        return false;
    }
}
