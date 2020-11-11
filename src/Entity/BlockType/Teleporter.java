package Entity.BlockType;

import java.awt.*;
import java.util.ArrayList;

import Entity.Block;
import Entity.Lemming;

public class Teleporter implements BlockType
{
    private final Block block;
    private int teleportToX = 0;
    private int teleportToY = 0;

    public Teleporter (Block block)
    {
        this.block = block;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(new Color(128, 0, 255));
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    @Override
    public boolean setOptionalArgs (int[] args)
    {
        if (args.length == 2)
        {
            teleportToX = args[0];
            teleportToY = args[1];
            return true;
        }
        return false;
    }

    @Override
    public boolean update ()
    {
        block.getGame().setLemmingLocation(block.getLemmingsOnBlock(), teleportToX, teleportToY);
        return true;
    }
}
