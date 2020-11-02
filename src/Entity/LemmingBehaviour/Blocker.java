package Entity.LemmingBehaviour;

import Entity.Lemming;

public class Blocker implements LemmingBehaviour
{
    private final Lemming lemming;

    public Blocker(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void tick()
    {
        // NYI
    }
}
