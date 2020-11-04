package Entity.BlockType;

import Entity.Block;

import java.awt.*;

public class Destructible implements BlockType
{
	private final Block block;

	public Destructible(Block block)
	{
		this.block = block;
	}

	@Override
	public void draw(Graphics graphics, int windowX, int windowY)
	{

	}

	@Override
	public void update()
	{

	}
}
