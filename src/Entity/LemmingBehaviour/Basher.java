package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Basher implements LemmingBehaviour
{
    private final Lemming lemming;
    private int blockDrill;

    public Basher(Lemming lemming)
    {
        this.blockDrill = 0;
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics g, int windowX, int windowY)
    {
        g.setColor(Color.RED);
        g.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }

    @Override
    public boolean update(ArrayList<Block> blocks, ArrayList<Lemming> lemmings)
    {
        boolean blockUpdated = false;
        boolean blockBelow;
        Block blockForward;

        blockBelow = lemming.findInferiorBlock();
        blockForward = lemming.getFrontBlock();
        blockUpdated = destroyBlock(blocks, lemmings, blockBelow, blockForward);
        updateLocation(blockUpdated, blockBelow, blockForward);

        return true;
    }

    private void updateLocation(boolean blockUpdated, boolean blockBelow, Block blockForward)
    {
        if (!blockBelow) lemming.setY(lemming.getY() + 1);
        else if (blockUpdated)
        {
            blockDrill++;
            updateHorizontalLocation();
        }
        else if (blockForward != null) lemming.changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
        else updateHorizontalLocation();
    }

    private void updateHorizontalLocation()
    {
        if (lemming.getDirection() == DirectionEnum.LEFT && lemming.getX() == 0)
        {
            lemming.setX(lemming.getX() + 1);
            lemming.changeDirectionTo(DirectionEnum.RIGHT);
        }
        else if (lemming.getDirection() == DirectionEnum.RIGHT && lemming.getX() == LemmingsGame.MAP_DIMENSION)
        {
            lemming.setX(lemming.getX() - 1);
            lemming.changeDirectionTo(DirectionEnum.LEFT);
        }
        else if (lemming.getDirection() == DirectionEnum.LEFT) lemming.setX(lemming.getX() - 1);
        else if (lemming.getDirection() == DirectionEnum.RIGHT) lemming.setX(lemming.getX() + 1);
    }

    // TODO problème de conception objet, on dirait qu'on est en C là, faire en sorte que cela utilise le deleteblock du modèle
    public boolean destroyBlock(ArrayList<Block> blocks, ArrayList<Lemming> lemmings, boolean blockBelow, Block blockForward)
    {
        boolean b = false;
        if (blockBelow && blockForward != null)
        {
            b = blockForward.update(blocks, lemmings);
        }
        return b;
    }

}
