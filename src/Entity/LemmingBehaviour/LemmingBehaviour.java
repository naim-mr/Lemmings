package Entity.LemmingBehaviour;

import Entity.Lemming;

public abstract class LemmingBehaviour
{
   protected Lemming lemming;

   abstract public boolean update(); // Action de chaque type de lemming à chaque tick.

    public boolean setToDelete ()
    {
        lemming.toDelete = true;
        return true;
    }
}
