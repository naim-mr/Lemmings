package Entity.LemmingBehaviour;

import Entity.Lemming;
import Game.LemmingsGameView;

import java.awt.*;

public abstract class LemmingBehaviour
{
   protected Lemming lemming;

   public void draw (Graphics g, int windowX, int windowY)
   {
      g.setColor(getColor());
      g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
   }

   abstract Color getColor ();
   abstract public boolean update(); // Action de chaque type de lemming à chaque tick.
}
