package Entity.BlockType;

import java.awt.*;
import java.util.ArrayList;

import Entity.*;

public interface BlockType
{
    void draw(Graphics graphics, int windowX, int windowY);

    default boolean setOptionalArgs (int[] args)
    {
        return false;
    }

    default boolean setOptionalArgs (ArrayList<Block> blocks)
    {
        return false;
    }

    default boolean update ()
    {
        return false;
    }

    default boolean destroy ()
    {
        return false;
    }
}
