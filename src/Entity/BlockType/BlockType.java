package Entity.BlockType;

import java.awt.*;
import java.util.ArrayList;

import Entity.*;

public abstract class BlockType
{
    protected Block block;

    public void setOptionalArgs (int[] args){}
    public void setOptionalArgs (ArrayList<Block> blocks) {
    }
    public boolean update ()
    {
        return false;
    }
    public boolean destroy ()
    {
        return false;
    }
}
