package Entity.BlockType;

import java.awt.*;
import java.util.ArrayList;

import Entity.*;

public interface BlockType
{
    void draw(Graphics graphics, int windowX, int windowY);
    boolean update();
    boolean destroy();
}
