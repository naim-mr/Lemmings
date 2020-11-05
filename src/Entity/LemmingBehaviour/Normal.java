package Entity.LemmingBehaviour;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import Game.*;
import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;

public class Normal implements LemmingBehaviour
{
    private final Lemming lemming;

    
    public Normal(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
    	g.setColor(Color.pink );
        g.fillRect(windowX,windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE); 
    }
   
    
    @Override
    public boolean update(ArrayList<Block> blocks, ArrayList<Lemming> lemmings){
           	          	
    			boolean blockBelow =false;
    		    boolean wall= false; 
    		    
    		    
    		    for (Block b : blocks)
    		    {
    		    		int[] blockCoord = LemmingsGameView.mapToWindowCoords(b.getX(), b.getY());
    		    		int[] lemmingCoord = LemmingsGameView.mapToWindowCoords(lemming.getX(), lemming.getY());
    		           	if( (blockCoord[1]==lemmingCoord[1]+LemmingsGameView.TILE_SIZE)  && blockCoord[0]==lemmingCoord[0]){
	    		    	    	   blockBelow = true;
	    		        }
	    		        if(lemming.getDirection()==DirectionEnum.RIGHT && ((b.getX() ==lemming.getX()+1 && b.getY()==lemming.getY()) ||lemming.getX()+1== LemmingsGame.MAP_DIMENSION )){
	    		        	wall=true;
	    		        }else if( lemming.getDirection()==DirectionEnum.LEFT &&(( b.getX()+1 ==lemming.getX() && b.getY()==lemming.getY()) ||lemming.getX()==0 )){
	    		        	wall=true;
	    		       }
    		      
    		   }
      		   if(!blockBelow ) {	
    			   lemming.setY(lemming.getY()+1);
    		    }else if(wall){
    		    	
    		    	if(lemming.getDirection()==DirectionEnum.RIGHT){
    		    		
    		    		lemming.changeDirectionTo(DirectionEnum.LEFT);
    		    		lemming.setX(lemming.getX()-1);
    		    	}else if(lemming.getDirection()==DirectionEnum.LEFT){
    		    		
    		    		lemming.setX(lemming.getX()+1);
    		    		lemming.changeDirectionTo(DirectionEnum.RIGHT);
    		    	}
    		  		  
    		    	wall=false;
    		   	}
    		   	else {
    		   		if(lemming.getDirection()==DirectionEnum.LEFT)lemming.setX(lemming.getX()-1);
    		   		if(lemming.getDirection()==DirectionEnum.RIGHT)lemming.setX(lemming.getX()+1);
    		   	}
    		   return true;
    		
        }
  

}
    

