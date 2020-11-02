package Entity.LemmingBehaviour;

import Entity.Lemming;

import java.awt.*;

public class Blocker implements LemmingBehaviour
{
    private final Lemming lemming;

    public Blocker(Lemming lemming)
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
