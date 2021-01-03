package Entity.BlockType;

import java.awt.Color;

import Entity.Block;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import java.awt.Graphics;

public class Spawner extends BlockType
{
    private int lemmingsNb;
    private int cooldown = 0;
    public static final Color color = Color.YELLOW;

    public Spawner(Block block)
    {
        this.block = block;
    }

    @Override
    public Color getColor ()
    {
        return color;
    }

    @Override
    public void setOptionalArgs (int[] args)
    {
        lemmingsNb = args[0];
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
