package Entity.BlockType;

import java.util.ArrayList;

import Entity.*;

public abstract class BlockType
{
    protected Block block;

    public boolean update ()
    {
        return false;
    }
    public boolean destroy ()
    {
        return false;
    }
    public void setNumberToSpawn (int nb) {}
    public void setBlocksToSpawn (ArrayList<Block> blocks) {}
    public void setTeleportTo (int x, int y) {}
}
