package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;

public interface BehaviourRefactor {

	 default void normalUpdateLocation(Lemming lemming ,boolean blockBelow, boolean wall,boolean step,boolean frontBlock)
	    {
		 boolean edgeDim = lemming.getX()== LemmingsGame.MAP_DIMENSION && lemming.getDirection()==DirectionEnum.RIGHT;;
		 boolean edgeZero= lemming.getX()==0 && lemming.getDirection()==DirectionEnum.LEFT;
		 if (((wall && frontBlock ) || (!step && frontBlock))|| edgeZero || edgeDim) manageFrontWall(lemming);
	     else if(step && !wall ) manageStep(lemming);
	     else manageNormalPace(lemming);  
	      
	    }

	 default void manageFrontWall(Lemming lemming) {
	
	    if (lemming.getDirection() == DirectionEnum.RIGHT)
	    {
	
	        lemming.changeDirectionTo(DirectionEnum.LEFT);
	        lemming.setX(lemming.getX() - 1);
	    }
	    else if (lemming.getDirection() == DirectionEnum.LEFT)
	    {
	
	        lemming.setX(lemming.getX() + 1);
	        lemming.changeDirectionTo(DirectionEnum.RIGHT);
	    }
	}
	 
	default void manageStep(Lemming lemming) {
		 if (lemming.getDirection() == DirectionEnum.RIGHT)
         {
    		 lemming.setY(lemming.getY() - 1);
    		 lemming.setX(lemming.getX() + 1);
         }
         else if (lemming.getDirection() == DirectionEnum.LEFT)
         {

        	 lemming.setY(lemming.getY() - 1);
    		 lemming.setX(lemming.getX() - 1);
             
         }
	}
	
	default void manageNormalPace(Lemming lemming) {
	       if (lemming.getDirection() == DirectionEnum.LEFT) lemming.setX(lemming.getX() - 1);
           if (lemming.getDirection() == DirectionEnum.RIGHT) lemming.setX(lemming.getX() + 1);
	}
	 
}
