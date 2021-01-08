package Game;

import Entity.Block;
import Entity.Lemming;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import java.util.ArrayList;
import java.util.List;

public class LemmingsGameObservable implements ILemmingsGame
{
    private final List<ILemmingsGameObserver> observers;
    private final LemmingsGame lemmingsGame;

    public LemmingsGameObservable ()
    {
        observers = new ArrayList<>();
        lemmingsGame = new LemmingsGame(this);
    }

    @Override
    public ArrayList<Block> getBlocks ()
    {
        return lemmingsGame.getBlocks();
    }

    @Override
    public ArrayList<Lemming> getLemmings ()
    {
        return lemmingsGame.getLemmings();
    }

    @Override
    public void changeSelectedBehaviour (LemmingBehaviourEnum behaviourEnum)
    {
        lemmingsGame.changeSelectedBehaviour(behaviourEnum);
    }

    @Override
    public LemmingBehaviourEnum getSelectedBehaviour ()
    {
        return lemmingsGame.getSelectedBehaviour();
    }

    @Override
    public void changeLemming (int mapX, int mapY)
    {
        lemmingsGame.changeLemming(mapX, mapY);
    }

    @Override
    public void step ()
    {
        lemmingsGame.step();
    }

    @Override
    public int getMapDimension ()
    {
        return lemmingsGame.getMapDimension();
    }

    public void registerObserver(ILemmingsGameObserver o) {
        observers.add(o);
    }

    public void unregisterObserver(ILemmingsGameObserver o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (ILemmingsGameObserver LObserver : observers) {
            LObserver.update();
        }
    }
}
