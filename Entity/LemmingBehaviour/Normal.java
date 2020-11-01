package Entity.LemmingBehaviour;

import java.awt.Graphics;

import Entity.Lemming;

public class Normal extends LemmingBehaviour
{
    private Lemming lemming;

    public Normal(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void tick()
    {
    	System.out.println("ok tick");
    	lemming.setX(lemming.getX()+1);
        // NYI
    }

	@Override
	public void draw (Graphics g,int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
