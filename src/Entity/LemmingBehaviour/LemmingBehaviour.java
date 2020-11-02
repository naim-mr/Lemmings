package Entity.LemmingBehaviour;

import java.awt.*;

public interface LemmingBehaviour
{
   void draw(Graphics graphics, int windowX, int windowY);
   void update(); // Action de chaque type de lemming à chaque tick.
}
