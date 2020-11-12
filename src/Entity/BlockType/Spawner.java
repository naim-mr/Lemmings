package Entity.BlockType;

import java.awt.Color;

import Entity.Block;
import Entity.Lemming;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import java.awt.Graphics;
import java.util.ArrayList;

public class Spawner implements BlockType
{
    private final Block block;
    private int lemmingsNb;
    private int cooldown = 0;
    public static Color color = Color.YELLOW;

    public Spawner(Block block)
    {
        this.block = block;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
    }

    @Override
    public boolean setOptionalArgs (int[] args)
    {
        lemmingsNb = args[0];
        return true;
    }

    @Override
    public boolean update ()
    {
        if (cooldown == 2)
        {
            cooldown = 0;
            spawnLemming();
        }
        cooldown++;
        return true;
    }

    private void spawnLemming ()
    {
        if (lemmingsNb > 0)
        {
            block.getGame().spawnLemming(block, LemmingBehaviourEnum.NORMAL);
            --lemmingsNb;
        }
    }
}
