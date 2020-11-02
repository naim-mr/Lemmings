package Entity;

import Entity.BlockType.*;

import java.awt.*;

public class Block extends Entity
{
    private BlockType blockType;

    // TODO : Factory method.
    public Block(BlockTypeEnum blockTypeEnum, int x, int y, int width, int height)
    {
        changeTypeTo(blockTypeEnum);
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        blockType.draw(graphics, windowX, windowY);
    }

    @Override
    public void update()
    {
        blockType.update();
    }

    private void changeTypeTo(BlockTypeEnum blockTypeEnum)
    {
        switch (blockTypeEnum)
        {
            case DESTRUCTIBLE_BLOCK:
                blockType = new Destructible(this);
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

}
