package Entity.BlockType;

import Entity.Block;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

public class Spawner extends BlockType
{
    private int lemmingsNb;
    private int cooldown = 0;

    public Spawner(Block block)
    {
        this.block = block;
        lemmingsNb = 0;
    }

    @Override
    public void setNumberToSpawn (int nb)
    {
        lemmingsNb = nb;
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
