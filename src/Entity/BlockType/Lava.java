package Entity.BlockType;

import Entity.Block;

public class Lava extends BlockType
{
	public Lava(Block block)
	{
		this.block = block;
	}

	@Override
	public boolean update ()
	{
		block.getGame().deleteLemming(block.getLemmingsOnBlock());
		return true;
	}
}
