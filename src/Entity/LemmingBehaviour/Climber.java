package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGameView;

import java.awt.*;

public class Climber implements LemmingBehaviour
{
    private final Lemming lemming;
    private boolean climbing;

    public Climber(Lemming lemming)
    {
        this.lemming = lemming;
        this.climbing = false;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
        g.setColor(Color.cyan);
        g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }


    @Override
    public boolean update ()
    {
        boolean blockBelow = lemming.findInferiorBlock();
        boolean wall = lemming.findFrontBlock();

        updateLocation(blockBelow, wall);
        return true;
    }

    private void updateLocation(boolean blockBelow, boolean wall)
    {
        if (!blockBelow && !climbing)
        {
            lemming.setY(lemming.getY() + 1);
            // si y'a pas de block en dessous et que le grimpeur ne grimpe pas
        }
        else if (wall)
        {
            // s'il y a un mur on grimpe
            climbing = true;
            lemming.setY(lemming.getY() - 1);
        }
        else if (!wall && climbing)
        {
            // s'il n'y a pas de mur et qu'on grimpe c'est qu'on est au sommet
            climbing = false;
            if (lemming.getDirection() == DirectionEnum.RIGHT)
            {
                lemming.setX(lemming.getX() + 1);
            }
            else if (lemming.getDirection() == DirectionEnum.LEFT)
            {
                lemming.setX(lemming.getX() - 1);

            }
            lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL); // Gestion des changements � sommet
        }
        else
        {
            //S'il y a aucun cas mouvement classique
            if (lemming.getDirection() == DirectionEnum.LEFT) lemming.setX(lemming.getX() - 1);
            if (lemming.getDirection() == DirectionEnum.RIGHT) lemming.setX(lemming.getX() + 1);
        }
    }
}
