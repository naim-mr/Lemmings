package Entity.BlockType;

import java.awt.Color;

import Entity.Block;
import Entity.Lemming;

import java.awt.Graphics;
import java.util.ArrayList;

public class Spawner implements BlockType
{
    private final Block block;
    private int lemmingsNb;

    public Spawner(Block block)
    {
        this.block = block;
    }

    @Override
    public void draw(Graphics g, int x, int y)
    {
        g.setColor(Color.RED);
    }

    @Override
    public void update()
    {

    }

    public void update (ArrayList<Lemming> lemmings)
    {
        // lemmings.add(new Lemming(this.x + 50, this.y + 50));
    }
}
