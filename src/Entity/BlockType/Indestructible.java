package Entity.BlockType;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Block;


public class Indestructible extends Block
{

	public Indestructible(int x, int y) {
		super(x, y,50,50);
	}

	@Override
	public void draw(Graphics g,int x, int y) {
		// TODO Auto-generated method stub
		  g.setColor(Color.BLUE);
	      g.fillRect(x, y, width,height);
	}
}
