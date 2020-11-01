package Entity.BlockType;

import java.awt.Color;
import game.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
