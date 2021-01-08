package Entity.BlockType;

import Entity.Block;

public class Teleporter extends BlockType
{
    private int teleportToX = 0;
    private int teleportToY = 0;

    public Teleporter (Block block)
    {
        this.block = block;
    }

    @Override
    public void setTeleportTo (int x, int y)
    {
        teleportToX = x;
        teleportToY = y;
    }

    @Override
    public boolean update ()
    {
        block.getGame().setLemmingLocation(block.getLemmingsOnBlock(), teleportToX, teleportToY);
        return true;
    }
}
