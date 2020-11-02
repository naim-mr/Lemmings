package Entity.LemmingBehaviour;

import Entity.Lemming;

public class Bomber implements LemmingBehaviour
{
    private final Lemming lemming;

    public Bomber(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void tick()
    {
        // NYI
    }
}
