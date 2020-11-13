package Entity.BlockType;

import Entity.Block;
import Entity.Lemming;

import java.awt.*;
import java.util.ArrayList;

public class DestructibleLandmine implements BlockType
{
    private final Block block;
    public static final Color color = new Color (120, 0, 70);

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(color);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    public DestructibleLandmine (Block block)
    {
        this.block = block;
    }

    @Override
    public boolean destroy ()
    {
        block.setToDelete();
        blast();
        return true;
    }

    private void blast()
    {
        ArrayList<Block> blocksToDelete = block.getGame().getBlocks((Block b) -> (b.getX() >= block.getX() - 2 && b.getX() <= block.getX() + 2) && (b.getY() >= block.getY() - 2 && b.getY() <= block.getY() + 2));
        ArrayList<Lemming> lemmingsToDelete = block.getGame().getLemmings((Lemming l) -> (l.getX() >= block.getX() - 2 && l.getX() <= block.getX() + 2) && (l.getY() >= block.getY() - 2 && l.getY() <= block.getY() + 2));
        blocksToDelete.remove(block);
        block.getGame().deleteLemming(lemmingsToDelete);
        block.getGame().deleteBlock(blocksToDelete);
    }
}
