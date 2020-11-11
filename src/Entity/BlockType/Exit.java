package Entity.BlockType;

import java.awt.*;
import java.util.ArrayList;

import Entity.Block;
import Entity.Lemming;

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
        graphics.setColor(new Color(128, 128, 0));
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    @Override
    public boolean update ()
    {
        block.getGame().onLemmingEscape(block.getLemmingsOnBlock());
    	return true;
    }
}
