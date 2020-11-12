package Entity.BlockType;

import Entity.Block;
import Entity.Lemming;

import java.awt.*;
import java.util.ArrayList;

public class DestructibleLandmine implements BlockType
{
    private Block block;
    public static Color color = new Color (120, 0, 70);

    @Override
    public void draw (Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(new Color (120, 0, 70));
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    public DestructibleLandmine (Block block)
    {
        this.block = block;
    }

    @Override
    public boolean setOptionalArgs (int[] args)
    {
        return false;
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
        // Explosion en croix des blocks en lemmings ( y'a pas d'explosion encore lol juste une disparition
        ArrayList<Block> blocksToDelete = block.getGame().getBlocks((Block b) -> (b.getX() == block.getX() + 1 && b.getY() == block.getY()) || (b.getX() == block.getX() - 1 && b.getY() == block.getY()));
        blocksToDelete.addAll(block.getGame().getBlocks((Block b) -> (b.getY() == block.getY() + 1 && b.getX() == block.getX()) || (b.getY() == block.getY() - 1 && b.getX() == block.getX())));

        ArrayList<Lemming> lemmingsToDelete = block.getGame().getLemmings((Lemming l) -> (l.getX() == block.getX() + 1 && l.getY() == block.getY()) || (l.getX() == block.getX() - 1 && l.getY() == block.getY()));
        lemmingsToDelete.addAll(block.getGame().getLemmings((Lemming l) -> (l.getY() == block.getY() + 1 && l.getX() == block.getX()) || (l.getY() == block.getY() - 1 && l.getY() == block.getX())));

        block.getGame().deleteLemming(lemmingsToDelete);
        block.getGame().deleteBlock(blocksToDelete);
    }
}
