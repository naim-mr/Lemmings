package Entity.LemmingBehaviour;

import java.awt.*;
import java.util.ArrayList;

import Entity.*;
public interface LemmingBehaviour
{
   void draw(Graphics graphics, int windowX, int windowY);
   boolean  update(ArrayList<Block> blocks,ArrayList<Lemming> lemmings); // Action de chaque type de lemming à chaque tick.
   
}
