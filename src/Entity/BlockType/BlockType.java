package Entity.BlockType;

import java.awt.*;
import java.util.ArrayList;

import Entity.*;

public interface BlockType
{
    void draw(Graphics graphics, int windowX, int windowY);

    default void setOptionalArgs (int[] args){}

    default void setOptionalArgs (ArrayList<Block> blocks) {
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
