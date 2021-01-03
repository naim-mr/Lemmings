package Entity.BlockType;

import java.awt.*;

import Entity.Block;

public class Indestructible extends BlockType
{
	public static final Color color = Color.BLACK;

	public Indestructible(Block block)
	{
		this.block = block;
	}

	@Override
	public Color getColor ()
	{
		return color;
	}
}
