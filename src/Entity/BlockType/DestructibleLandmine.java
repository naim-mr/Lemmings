package Entity.BlockType;

import Entity.Block;
import Entity.Lemming;

import java.util.ArrayList;

public class DestructibleLandmine extends BlockType
{
    public DestructibleLandmine (Block block)
    {
        this.block = block;
    }

    @Override
    public boolean update ()
    {
        if (block.toDelete) blast();
        return true;
    }

    private void blast()
    {
        block.destroyNearbyEntities();
    }
}
