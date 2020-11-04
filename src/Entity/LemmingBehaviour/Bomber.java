package Entity.LemmingBehaviour;

import Entity.Lemming;

import java.awt.*;

public class Bomber implements LemmingBehaviour
{
    private final Lemming lemming;

    public Bomber(Lemming lemming)
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
        // NYI
    }
}
