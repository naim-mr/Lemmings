package Entity.BlockType;

import java.awt.*;

import Entity.Block;

public class Exit implements BlockType
{
    private final Block block;
    public static final Color color = new Color(128, 128, 0);

    public Exit(Block block)
    {
        this.block = block;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(color);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    @Override
    public boolean update ()
    {
        block.getGame().onLemmingEscape(block.getLemmingsOnBlock());
    	return true;
    }
}
