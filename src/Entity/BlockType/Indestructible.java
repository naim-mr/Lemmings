package Entity.BlockType;

import Entity.Block;

public class Indestructible extends BlockType
{
	public Indestructible(Block block)
	{
		this.block = block;
	}

	@Override
	public boolean setToDelete ()
	{
		return false;
	}
}
