package Entity.BlockType;

import Entity.Block;
import Entity.BlockObservable;
import Entity.Lemming;
import Entity.LemmingObservable;

import java.awt.*;
import java.util.ArrayList;

public class DestructibleLandmine extends BlockType
{
    public static final Color color = new Color (120, 0, 70);

    @Override
    public Color getColor ()
    {
        return color;
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
        ArrayList<BlockObservable> blocksToDelete = block.getGame().getBlocks(this::nearbyEntities);
        ArrayList<LemmingObservable> lemmingsToDelete = block.getGame().getLemmings(this::test);
        BlockObservable blockObs = new BlockObservable( block);//Ici on doit créer un blockObservable , et le remove fait le taff car j'ai override equal dans la class 
        blocksToDelete.remove(blockObs);
        block.getGame().deleteLemming(lemmingsToDelete);
        block.getGame().deleteBlock(blocksToDelete);
    }
 // todo
    private boolean nearbyEntities (BlockObservable b)
    {
        return (b.getX() >= block.getX() - 2 && b.getX() <= block.getX() + 2) && (b.getY() >= block.getY() - 2 && b.getY() <= block.getY() + 2);
    }

    private boolean test (LemmingObservable l)
    {
        return l.getX() >= block.getX() - 2 && l.getX() <= block.getX() + 2 && l.getY() >= block.getY() - 2 && l.getY() <= block.getY() + 2;
    }
}
