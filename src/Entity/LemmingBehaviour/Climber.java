package Entity.LemmingBehaviour;

import Entity.Lemming;

public class Climber implements LemmingBehaviour
{
    private final Lemming lemming;

    public Climber(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void tick()
    {
        // NYI
    }
}
