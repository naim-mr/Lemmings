package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

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


    // TODO refactor
    @Override
    public boolean update ()
    {
        ArrayList<Block> blocks = lemming.getGame().getBlocks();
        ArrayList<Lemming> lemmings = lemming.getGame().getLemmings();

        boolean blockBelow = lemming.findInferiorBlock();
        boolean wall = false;

        for (Block b : blocks)
        {
            int[] blockCoord = LemmingsGameView.mapToWindowCoords(b.getX(), b.getY()); // ?????????
            int[] lemmingCoord = LemmingsGameView.mapToWindowCoords(lemming.getX(), lemming.getY()); // ???????????????????????????????????????????????
            if (blockCoord[1] <= lemmingCoord[1] + LemmingsGameView.TILE_SIZE && blockCoord[0] == lemmingCoord[0])
            {
                blockBelow = true; // Block en dessous ?
            }

            if (lemming.getDirection() == DirectionEnum.RIGHT && (b.getX() <= lemming.getX() + 1 && b.getY() == lemming.getY() || lemming.getX() + 1 == LemmingsGame.MAP_DIMENSION))
            {
                wall = true; // Si le lemming va vers la droite et qu'il y a un block ou le bord de la map

            }
            else if (lemming.getDirection() == DirectionEnum.LEFT && ((b.getX() + 1 == lemming.getX() && b.getY() == lemming.getY()) || lemming.getX() == 0))
            {
                wall = true; // la m�me vers la gauche
            }
        }


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
            wall = false;
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
        return true;
    }


}
