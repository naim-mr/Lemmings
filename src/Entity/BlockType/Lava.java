package Entity.BlockType;

import java.awt.Color;

import Entity.Block;

public class Lava extends BlockType
{
	public static final Color color = Color.RED;

	public Lava(Block block)
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
		block.getGame().deleteLemming(block.getLemmingsOnBlock());
		return true;
	}
}
