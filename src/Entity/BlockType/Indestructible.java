package Entity.BlockType;

import java.awt.*;
import java.util.ArrayList;

import Entity.Block;
import Entity.Lemming;

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
		graphics.setColor(Color.BLACK);
		graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
	}

	@Override
	public boolean update ()
	{
		return false;
	}

	@Override
	public boolean destroy()
	{
		return false;
	}
}
