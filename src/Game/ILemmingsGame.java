package Game;

import Entity.Block;
import Entity.Lemming;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import java.util.ArrayList;

public interface ILemmingsGame
{
    ArrayList<Block> getBlocks ();
    ArrayList<Lemming> getLemmings ();
    void changeSelectedBehaviour (LemmingBehaviourEnum behaviourEnum);
    LemmingBehaviourEnum getSelectedBehaviour ();
    void changeLemming (int mapX, int mapY);
    int getMapDimension();
    void step();
}
