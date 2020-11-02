package Entity.LemmingBehaviour;

import java.awt.Graphics;

import Entity.Lemming;

public class Normal implements LemmingBehaviour
{
    private final Lemming lemming;

    public Normal(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {

    }

    @Override
    public void update()
    {
    	System.out.println("ok tick");
    	lemming.setX(lemming.getX()+1);
        // NYI
    }

}
