package Entity.BlockType;

import Entity.Block;
import Entity.Lemming;

import java.awt.*;
import java.util.ArrayList;

public class DestructibleLandmine implements BlockType
{
    private Block block;
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
    	 ArrayList<Block> blocksToDelete = block.getGame().getBlocks((Block b) ->( (b.getX() >= block.getX()-2  && b.getX()<=block.getX()  && (b.getY() >= block.getY()-2 && b.getY() <= block.getY()+2))  || ((b.getX() < block.getX()+2 && b.getX()>=block.getX() && (b.getY() >= block.getY()-2 && b.getY() <= block.getY()+2)) )));
          //blocksToDelete.addAll(lemming.getGame().getBlocks((Block b) -> (b.getY() == lemming.getY() + 1 && b.getX() == lemming.getX()) || (b.getY() == lemming.getY() - 1 && b.getX() == lemming.getX())));
         ArrayList<Lemming> lemmingsToDelete =  block.getGame().getLemmings((Lemming l)->( (l.getX() >= block.getX()-2  && l.getX()<=block.getX()  && (l.getY() >= block.getY()-2 && l.getY() <= block.getY()+2))  || ((l.getX() < block.getX()+2 && l.getX()>=block.getX() && (l.getY() >= block.getY()-2 && l.getY() <= block.getY()+2)) )));
         // lemmingsToDelete.addAll(lemming.getGame().getLemmings((Lemming l) -> (l.getY() == lemming.getY() + 1 && l.getX() == lemming.getX()) || (l.getY() == lemming.getY() - 1 && l.getY() == lemming.getX())));
         blocksToDelete.add(this.block);
         block.getGame().deleteLemming(lemmingsToDelete);
         block.getGame().deleteBlock(blocksToDelete);
    }
}
