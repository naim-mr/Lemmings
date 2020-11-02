package Entity.BlockType;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Block;

public class Lava implements BlockType
{
	@Override
	public void draw(Graphics graphics, int windowX, int windowY)
	{
		graphics.setColor(Color.RED);
	}
}
