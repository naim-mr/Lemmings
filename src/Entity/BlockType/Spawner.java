package Entity.BlockType;

import java.awt.Color;

import Entity.Block;
import Entity.Lemming;

import java.awt.Graphics;
import java.util.ArrayList;

public class Spawner extends Block
{
	
	public Spawner(int x, int y) {
		super(x, y,50,50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g,int x , int y ) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
        g.fillOval(x, y, width, height);
        
	}
	
	public void spawn(ArrayList<Lemming> lemmings) {
		lemmings.add(new Lemming(this.x+50,this.y+50));
		
	}
	
	
	
}
