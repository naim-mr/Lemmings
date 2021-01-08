package Entity.BlockType;

import Entity.Block;

public class Exit extends BlockType
{
    public Exit(Block block)
    {
        this.block = block;
    }

    @Override
    public boolean update ()
    {
        block.getGame().onLemmingEscape(block.getLemmingsOnBlock());
    	return true;
    }
}
