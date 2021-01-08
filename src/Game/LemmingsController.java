package Game;

import static Entity.LemmingBehaviour.LemmingBehaviourEnum.*;

public class LemmingsController
{
    private final LemmingsGameObservable game;
    private LemmingsGameView view;

    public LemmingsController (LemmingsGameObservable game)
    {
        this.game = game;
    }

    public void setLemmingsGameView (LemmingsGameView lemmingsGameView)
    {
        view = lemmingsGameView;
    }
    public void onClick (int mapX, int mapY)
    {
        if (mapY < game.getMapDimension())
        {
            onGameClick(mapX, mapY);
        }
        onMenuClick(mapX, mapY);
        view.repaint();
    }

    private void onGameClick (int mapX, int mapY)
    {
        game.changeLemming(mapX, mapY);
    }

    private void onMenuClick (int mapX, int mapY)
    {
        if (mapY == game.getMapDimension() + 1)
        {
            switch (mapX)
            {
                case 1 :
                    game.changeSelectedBehaviour(BASHER);
                    break;
                case 3 :
                    game.changeSelectedBehaviour(BLOCKER);
                    break;
                case 5 :
                    game.changeSelectedBehaviour(BOMBER);
                    break;
                case 7 :
                    game.changeSelectedBehaviour(CLIMBER);
                    break;
                case 9 :
                    game.changeSelectedBehaviour(DIGGER);
                    break;
                case 11 :
                    game.changeSelectedBehaviour(FLOATER);
                    break;
                case 13 :
                    game.changeSelectedBehaviour(CARPENTER);
                    break;
            }
        }
    }
}
