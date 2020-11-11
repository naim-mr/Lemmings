package Game;

import Entity.*;
import Entity.BlockType.BlockTypeEnum;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import java.util.ArrayList;

public class LemmingsGame
{
    private final ArrayList<Block> blocks;
    private final ArrayList<Lemming> lemmings;
    private LemmingsGameView lemmingsGameView;
    private LemmingBehaviourEnum selectedBehaviour = LemmingBehaviourEnum.NORMAL;

    public static final int MAP_DIMENSION = 20;
    private int escapedLemmings = 0;

    public LemmingsGame ()
    {
        blocks = new ArrayList<>();
        lemmings = new ArrayList<>();

        CreateTestMap(); // TEMP
    }

    // NE PAS MODIFIER
    public ArrayList<Block> getBlocks ()
    {
        return new ArrayList<Block>(blocks);
    }

    // NE PAS MODIFIER
    public ArrayList<Lemming> getLemmings ()
    {
        return new ArrayList<Lemming>(lemmings);
    }

    public ArrayList<Block> getBlocks (BlockCondition blockCondition)
    {
        ArrayList<Block> outBlock = new ArrayList<>();
        for (Block b : blocks)
        {
            if (blockCondition.test(b)) outBlock.add(b);
        }
        return outBlock;
    }

    public ArrayList<Lemming> getLemmings (LemmingCondition lemmingCondition)
    {
        ArrayList<Lemming> outLemming = new ArrayList<>();
        for (Lemming l : lemmings)
        {
            if (lemmingCondition.test(l)) outLemming.add(l);
        }
        return outLemming;
    }

    public void setLemmingsGameView (LemmingsGameView lemmingsGameView)
    {
        this.lemmingsGameView = lemmingsGameView;
    }

    public void CreateTestMap ()
    {
        Block spawner = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 0, 4);
        spawner.setOptionalArgs(3);
        blocks.add(spawner);

        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 0, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 1, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 2, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 3, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 4, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 5, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 6, 5));

        Block blockSpawner = new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 7, 4);
        ArrayList<Block> spawnerList = new ArrayList<>();
        for (int i = 0; i < MAP_DIMENSION; ++i)
        {
            spawnerList.add(new Block(this, BlockTypeEnum.LAVA_BLOCK, i, 19));
        }
        blockSpawner.setOptionalArgs(spawnerList);

        blocks.add(blockSpawner);
    }

    private void update ()
    {
        lemmings.removeIf(Entity::getToDelete);
        blocks.removeIf(Entity::getToDelete);

        for (Lemming l : getLemmings())
        {
            l.update();
        }

        for (Block b : blocks)
        {
            b.update();
        }
    }

    // Main loop
    /*FONCTION QUI VA GERER La boucle de jeu*/
    public void gameLoop ()
    {
        boolean gameOver = false;

        while (!gameOver)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            update();
            lemmingsGameView.repaint();
        }
    }

    public void spawnLemming (Block atBlock, LemmingBehaviourEnum lemmingBehaviour)
    {
        lemmings.add(new Lemming(this, lemmingBehaviour, atBlock.getX(), atBlock.getY()));
    }

    public void changeLemming (int mapX, int mapY)
    {
        for (Lemming l : getLemmings())
        {
            if (l.getX() == mapX && l.getY() == mapY) l.changeBehaviourTo(selectedBehaviour);
        }
    }

    public void changeSelectedBehaviour (LemmingBehaviourEnum blockTypeEnum)
    {
        selectedBehaviour = blockTypeEnum;
    }

    public LemmingBehaviourEnum getSelectedBehaviour ()
    {
        return selectedBehaviour;
    }

    public boolean deleteBlock (Block b)
    {
        if (b != null) {
        	      	return b.destroy();
        }
        else return false;
    }

    public void deleteLemming (Lemming l)
    {
        if (l != null) l.setToDelete();
    }

    public void deleteBlock (ArrayList<Block> blockArrayList)
    {
        for (Block b : blockArrayList)
        {
            deleteBlock(b);
        }
    }

    public void deleteLemming (ArrayList<Lemming> lemmingsToDelete)
    {
        for (Lemming l : lemmingsToDelete)
        {
            deleteLemming(l);
        }
    }

    public void setLemmingLocation (Lemming l, int x, int y)
    {
        if (l != null)
        {
            l.setX(x);
            l.setY(y);
        }
    }

    public void setLemmingLocation (ArrayList<Lemming> lemmings, int x, int y)
    {
        for (Lemming l : lemmings)
        {
            setLemmingLocation(l, x, y);
        }
    }

    public void onLemmingEscape (Lemming l)
    {
        ++escapedLemmings;
        deleteLemming(l);
    }

    public void onLemmingEscape (ArrayList<Lemming> lemmings)
    {
        for (Lemming l : lemmings)
        {
            onLemmingEscape(l);
        }
    }

    public void createBlock (ArrayList<Block> b)
    {
        blocks.addAll(b);
    }
}
