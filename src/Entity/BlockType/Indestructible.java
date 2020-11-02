package Entity.BlockType;

import java.awt.Graphics;

import Entity.Block;

public class Indestructible implements BlockType
{
	private final Block block;

	public Indestructible(Block block)
	{
		this.block = block;
	}

	@Override
	public void draw(Graphics graphics, int windowX, int windowY)
	{
		graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
	}

	@Override
	public void update()
	{

	}
}
