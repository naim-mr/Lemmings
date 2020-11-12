package Entity.BlockType;

import java.awt.*;

import Entity.Block;

public class Indestructible implements BlockType
{
	private final Block block;
	public static final Color color = Color.BLACK;

	public Indestructible(Block block)
	{
		this.block = block;
	}

	@Override
	public void draw(Graphics graphics, int windowX, int windowY)
	{
		graphics.setColor(color);
		graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
	}
}
