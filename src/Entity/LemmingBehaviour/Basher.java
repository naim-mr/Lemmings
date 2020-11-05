package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Basher implements LemmingBehaviour
{
    private final Lemming lemming;
    private int blockDrill;
    public Basher(Lemming lemming)
    {	
    	this.blockDrill=0;
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
    	g.setColor(Color.RED );
        g.fillRect(windowX,windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }
	
    @Override
    public boolean update(ArrayList<Block> blocks, ArrayList<Lemming> lemmings) {
		// TODO Auto-generated method stub
    	
		boolean blockUpdated=false;
		boolean blockBelow=false;
		Block blockForward=null;
		
		int i=0;
		// On cherche les block en dessous et les blocks à 1 case plus loin selon la direction
		while(i<blocks.size() ) {
			if( blocks.get(i).getY()==lemming.getY()+1 && blocks.get(i).getX()==lemming.getX()) {
					blockBelow =true;
					//TODO gerer les pb de null
			}
			if(blocks.get(i).getX()==lemming.getX()+1 &&  blocks.get(i).getY()==lemming.getY() && lemming.getDirection()==DirectionEnum.RIGHT) {
				blockForward=blocks.get(i);
			}
			if(blocks.get(i).getX()==lemming.getX()-1 && blocks.get(i).getY()==lemming.getY() && lemming.getDirection()==DirectionEnum.LEFT) {
				blockForward=blocks.get(i);
			}
			i++;
		}
		// Comme pour les digger dans l'idée 
        if(blockBelow && blockForward!=null) {
			blockUpdated=blockForward.update(blocks, lemmings); 
			
		} 
        if(!blockBelow) {
			lemming.setY(lemming.getY()+1);
		}
        else if(blockUpdated) {
			blockDrill++;
			if(lemming.getDirection()==DirectionEnum.LEFT && lemming.getX()==0) {
				lemming.setX(lemming.getX()+1);
				lemming.changeDirectionTo(DirectionEnum.RIGHT);
			}
			else if(lemming.getDirection()==DirectionEnum.RIGHT && lemming.getX()==LemmingsGame.MAP_DIMENSION) {
	   			lemming.setX(lemming.getX()-1);
				lemming.changeDirectionTo(DirectionEnum.LEFT);
	   		}
	   		else if(lemming.getDirection()==DirectionEnum.LEFT)lemming.setX(lemming.getX()-1);
	   		else if(lemming.getDirection()==DirectionEnum.RIGHT)lemming.setX(lemming.getX()+1);
			
		}
        else if(blockBelow &&  blockForward!= null) {
			
			lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
						
		}
        else {
	
			if(lemming.getDirection()==DirectionEnum.LEFT && lemming.getX()==0) {
				lemming.setX(lemming.getX()+1);
				lemming.changeDirectionTo(DirectionEnum.RIGHT);
			}
			else if(lemming.getDirection()==DirectionEnum.RIGHT && lemming.getX()==LemmingsGame.MAP_DIMENSION) {
				lemming.setX(lemming.getX()-1);
				lemming.changeDirectionTo(DirectionEnum.LEFT);
	   		}
	   		else if(lemming.getDirection()==DirectionEnum.LEFT)lemming.setX(lemming.getX()-1);
	   		else if(lemming.getDirection()==DirectionEnum.RIGHT)lemming.setX(lemming.getX()+1);
		}
        	return true;
	
	}

}
