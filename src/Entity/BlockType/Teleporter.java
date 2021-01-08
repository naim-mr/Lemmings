package Entity.BlockType;

import java.awt.*;

import Entity.Block;

public class Teleporter extends BlockType
{
    private int teleportToX = 0;
    private int teleportToY = 0;

    public Teleporter (Block block)
    {
        this.block = block;
    }

    @Override
    public void setOptionalArgs (int[] args)
    {
        if (args.length == 2)
        {
            teleportToX = args[0];
            teleportToY = args[1];
        }
    }

    @Override
    public boolean update ()
    {
        block.getGame().setLemmingLocation(block.getLemmingsOnBlock(), teleportToX, teleportToY);
        return true;
    }
}
