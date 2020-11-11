package Entity.LemmingBehaviour;

import java.awt.*;

public interface LemmingBehaviour
{
   void draw(Graphics graphics, int windowX, int windowY);
   boolean update(); // Action de chaque type de lemming à chaque tick.
}
