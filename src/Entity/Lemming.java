package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Entity.LemmingBehaviour.LemmingBehaviour;
import Entity.LemmingBehaviour.Normal;
import game.*;
public class Lemming extends Entity
{
    public LemmingBehaviour behaviour;
    
    public static boolean [] life= {true,true,true};

    public Lemming (int x, int y)
    {	
    	this.x=x;
    	this.y=y;
    	this.width=25;
    	this.height = 35;
        behaviour = new Normal(this);
    }
    
    public boolean tick(ArrayList<Block> blocks ) {
    	Block blockBelow = null; 
    	for(Block b :blocks) {
    		
    		int [] blockCoord=	 LemmingsGameView.mapToWindowCoords(b.getX(),b.getY());
    		int [] lemmingCoord= LemmingsGameView.mapToWindowCoords(x,y);
    		if(b.getX()==this.getX()+this.height) {
    			
    				blockBelow=b;
    			}
    	}
    	
    	if(blockBelow!=null) {
    		
    		this.y ++ ; 
    		if(life[0] || life[1]) {
    			life[0]=true;
    			life[1]=true;
    			life[2]=true;
    			return true;
    		}
    		return true;
    	}
    	else {
    		if (life[0]) life[0]=false;
    		else if (life[1]) life[1]=false;
    		else life[2]=false;
    		this.x++;
    		return true;
    	} 
    }
    
    
    
	@Override
	public void draw(Graphics g, int x, int y) {
		
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		
		g.fillRect(x, y, width,height);
		
	}
	
}
