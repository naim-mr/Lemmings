package Entity.BlockType;

import java.awt.Graphics;
import java.util.ArrayList;

import Entity.Block;
import Entity.Lemming;

public class Teleporter implements BlockType
{
    private final Block block;

    public Teleporter(Block block)
    {
        this.block = block;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {

    }

    @Override
    public boolean update(ArrayList<Block> blocks,ArrayList<Lemming> lemmings)
    {
    	return true;
    }
}
