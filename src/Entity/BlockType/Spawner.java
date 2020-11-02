package Entity.BlockType;

import java.awt.Color;

import Entity.Block;
import Entity.Lemming;

import java.awt.Graphics;
import java.util.ArrayList;

public class Spawner implements BlockType
{
    @Override
    public void draw(Graphics g, int x, int y)
    {
        g.setColor(Color.RED);
    }

    public void spawn(ArrayList<Lemming> lemmings)
    {
        // lemmings.add(new Lemming(this.x + 50, this.y + 50));
    }
}
