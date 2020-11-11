package Entity;

import Entity.BlockType.*;
import Game.LemmingsGame;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Block extends Entity
{
    private BlockType blockType;

    public Block (LemmingsGame game, BlockTypeEnum blockType, int x, int y)
    {
        changeTypeTo(blockType);
        this.game = game;
        this.x = x;
        this.toDelete = false;
        this.y = y;
        this.width = LemmingsGameView.TILE_SIZE;
        this.height = LemmingsGameView.TILE_SIZE;
    }

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        blockType.draw(graphics, windowX, windowY);
    }

    private void changeTypeTo (BlockTypeEnum blockTypeEnum)
    {
        switch (blockTypeEnum)
        {
            case DESTRUCTIBLE_BLOCK_GROUND:

                blockType = new Destructible(this, DestructibleTypeEnum.GROUND);
                break;
            case DESTRUCTIBLE_BLOCK_HIDDENBLOCK:

                blockType = new Destructible(this, DestructibleTypeEnum.HIDDEN_BLOCK);
                break;
            case DESTRUCTIBLE_BLOCK_LANDMINE:

                blockType = new Destructible(this, DestructibleTypeEnum.LANDMINE);
                break;
            case EMPTY_BLOCK:
                blockType = new Empty(this);
                break;
            case EXIT_BLOCK:
                blockType = new Exit(this);
                break;
            case INDESTRUCTIBLE_BLOCK:
                blockType = new Indestructible(this);
                break;
            case LAVA_BLOCK:
                blockType = new Lava(this);
                break;
            case SPAWNER_BLOCK:
                blockType = new Spawner(this);
                break;
            case TELEPORTER_BLOCK:
                blockType = new Teleporter(this);
                break;
        }
    }

    // TODO : update des blocs est utilisé pour supprimer les blocs, il va falloir changer ça.
    @Override
    public boolean update ()
    {
        return blockType.update();
    }

    public boolean destroy ()
    {
        return blockType.destroy();
    }

    public ArrayList<Lemming> getLemmingsOnBlock ()
    {
        return getGame().getLemmings((Lemming l) -> l.getX() == getX() && l.getY() == getY() - 1);
    }
}
