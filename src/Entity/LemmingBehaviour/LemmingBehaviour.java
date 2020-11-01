package Entity.LemmingBehaviour;

import Entity.Drawable;

public abstract class LemmingBehaviour implements Drawable
{
   public abstract void tick(); // Action de chaque type de lemming à chaque tick.
}
