package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import Game.LemmingsGame;

public class EntityObservable implements IEntity{
		    
			
			protected Entity entity;
		    protected ArrayList<EntityObserver> observers;
		    
		    
		    public EntityObservable(Entity e ) {
		    
		    	entity=e;
		    	observers =new ArrayList<>();
		    }
		    
		    public void setToDelete()
		    {
		         entity.setToDelete();
		    }

		    public boolean getToDelete()
		    {
		        return entity.toDelete;
		    }


		    public int getX()
		    {
		        return entity.getX();
		    }

		    public int getY()
		    {
		        return entity.getY();
		    }

		    public void setX(int x)
		    {
		         entity.setX(x);
		    }

		    public void setY(int y)
		    {
		    	 entity.setY(y);
		    }
		    
		    public void register(EntityObserver e) {
		    	observers.add(e);
		    }
		    
		    public void unregister(EntityObserver e){
		    	observers.remove(e);
		    }
		    
		    void notifyObservers() {
		    	for(EntityObserver entityObserver : observers ) {
		    		entityObserver.update();
		    	}
		    }

		    public  void draw(Graphics graphics, int windowX, int windowY) {
		    	entity.draw(graphics, windowX, windowY);
		    }
	    
			@Override
		    public boolean update() {
	        	boolean bool = entity.update(); 
	        	notifyObservers();
	        	return bool;
	        	
	        }







			




				



	


}
