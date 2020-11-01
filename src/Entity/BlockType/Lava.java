package Entity.BlockType;

import java.awt.Color;
import java.awt.Graphics;

import game.*;

public class Lava extends Block
{

	public Lava(int x, int y,int width, int height) {
		super(x, y,width,height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g,int x, int y) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
	}
}
