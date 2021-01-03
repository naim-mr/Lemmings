package Entity.BlockType;

import java.awt.*;

import Entity.Block;

public class Exit extends BlockType
{
    public static final Color color = new Color(128, 128, 0);

    public Exit(Block block)
    {
        this.block = block;
    }

    @Override
    public Color getColor ()
    {
        return color;
    }

    @Override
    public boolean update ()
    {
        block.getGame().onLemmingEscape(block.getLemmingsOnBlock());
    	return true;
    }
}
