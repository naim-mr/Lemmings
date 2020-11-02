package Entity.LemmingBehaviour;

import Entity.Lemming;

public class Digger implements LemmingBehaviour
{
    private final Lemming lemming;

    public Digger(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void tick()
    {
        // NYI
    }
}
