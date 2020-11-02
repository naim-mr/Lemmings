package Entity.LemmingBehaviour;

import java.awt.Graphics;

import Entity.Lemming;

public class Normal extends LemmingBehaviour
{
    private final Lemming lemming;

    public Normal(Lemming lemming)
    {
        super(lemming);
        this.lemming = lemming;
    }

    @Override
    public void tick()
    {
    	System.out.println("ok tick");
    	lemming.setX(lemming.getX()+1);
        // NYI
    }

}
