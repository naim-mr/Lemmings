package Entity.BlockType;

import Entity.Block;
import Entity.Lemming;

import java.awt.*;
import java.util.ArrayList;

public class Destructible implements BlockType
{
    private Block block;
    private DestructibleTypeEnum type;
    private Block hiddenBlock;// � g�rer mieux que �a surement via h�ritage

    public Destructible(Block block, DestructibleTypeEnum type, Block hiddenBlock)
    {
        this.hiddenBlock = hiddenBlock;
        this.type = type;
        this.block = block;
    }

    public Destructible(Block block, DestructibleTypeEnum type)
    {
        this.hiddenBlock = null;
        this.type = type;
        this.block = block;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());

    }

    // TODO : ca c'est comme si on codait en impératif
    @Override
    public boolean destroy()
    {
        switch (type)
        {
            case GROUND:
                block.setToDelete();
                break;
            case LANDMINE:
                
                block.setToDelete();
                blast();
                break;
            case HIDDEN_BLOCK:
                block.setToDelete();
                // blocks.add(this.hiddenBlock);
                break;
        }
        return true;
    }

    @Override
    public boolean update()
    {
        return true;
    }

    private void blast()
    {
        // Explosion en croix des blocks en lemmings ( y'a pas d'explosion encore lol juste une disparition
        ArrayList<Block> blocksToDelete = block.getGame().getBlocks((Block b) -> (b.getX() == block.getX() + 1 && b.getY() == block.getY()) || (b.getX() == block.getX() - 1 && b.getY() == block.getY()));
        blocksToDelete.addAll(block.getGame().getBlocks((Block b) -> (b.getY() == block.getY() + 1 && b.getX() == block.getX()) || (b.getY() == block.getY() - 1 && b.getX() == block.getX())));

        ArrayList<Lemming> lemmingsToDelete = block.getGame().getLemmings((Lemming l) -> (l.getX() == block.getX() + 1 && l.getY() == block.getY()) || (l.getX() == block.getX() - 1 && l.getY() == block.getY()));
        lemmingsToDelete.addAll(block.getGame().getLemmings((Lemming l) -> (l.getY() == block.getY() + 1 && l.getX() == block.getX()) || (l.getY() == block.getY() - 1 && l.getY() == block.getX())));

        block.getGame().deleteLemming(lemmingsToDelete);
        block.getGame().deleteBlock(blocksToDelete);
    }
}