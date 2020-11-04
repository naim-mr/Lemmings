package Entity.BlockType;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Block;

public class Lava implements BlockType
{
	private final Block block;

	public Lava(Block block)
	{
		this.block = block;
	}

	@Override
	public void draw(Graphics graphics, int windowX, int windowY)
	{
		graphics.setColor(Color.RED);
		graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
	}

	@Override
	public void update()
	{

	}
}
