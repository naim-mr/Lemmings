package Game;


import Entity.Block;
import Entity.BlockType.BlockTypeEnum;
import Entity.BlockCondition;
import Entity.Lemming;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;
import Entity.LemmingCondition;

import java.util.ArrayList;

public class LemmingsGame
{
    private final ArrayList<Block> blocks;
    private final ArrayList<Lemming> lemmings;
    private LemmingsGameView lemmingsGameView;
    private Block spawner;
    private Block exit;
    private LemmingBehaviourEnum selectedBehaviour = LemmingBehaviourEnum.NORMAL;
    public static final int MAP_DIMENSION = 20;


    public LemmingsGame()
    {
        blocks = new ArrayList<>();
        lemmings = new ArrayList<>();

        CreateTestMap(); // TEMP
    }
    // NE PAS MODIFIER
    public ArrayList<Block> getBlocks()
    {
        return new ArrayList<Block>(blocks);
    }
    // NE PAS MODIFIER
    public ArrayList<Lemming> getLemmings()
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

    public void setLemmingsGameView(LemmingsGameView lemmingsGameView)
    {
        this.lemmingsGameView = lemmingsGameView;
    }

    public void CreateTestMap()
    {
        spawner = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 0, 4);
        blocks.add(spawner);
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 0, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 1, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 2, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 3, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 4, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 5, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 6, 5));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 7, 5));
        
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 2, 3));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 4, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 4, 1));
        //blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 3, 4));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 4, 4));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 5, 4));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 6, 4));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 7, 4));

    }

    private void update()
    {
        ArrayList<Block> blocksDeleted = new ArrayList<>();
        ArrayList<Lemming> lemmingsDeleted = new ArrayList<>(
        		);
        for (Lemming l : getLemmings())
        {
            l.update();
        }
        for (Lemming l : lemmings)
        {
            if (l.getToDelete()) lemmingsDeleted.add(l);
        }
        for (Block b : blocks)
        {
            if (b.getToDelete()) blocksDeleted.add(b);
        }
        blocks.removeAll(blocksDeleted);
        lemmings.removeAll(lemmingsDeleted);
    }

    // Main loop
    /*FONCTION QUI VA GERER La boucle de jeu*/
    public void gameLoop()
    {
        boolean gameOver = false;
        int k = 0;
        
        
        while (!gameOver) {
        	
        	if(k==2) {
        		spawnLemming(spawner, LemmingBehaviourEnum.NORMAL);
        		k=0;
        	}
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
            k++;
        }
    }

    private void spawnLemming(Block atBlock, LemmingBehaviourEnum lemmingBehaviour)
    {
        lemmings.add(new Lemming(this, lemmingBehaviour, atBlock.getX(), atBlock.getY()));
    }

    public void changeLemming(int mapX, int mapY)
    {
        for (Lemming l : getLemmings())
        {
            if (l.getX() == mapX && l.getY() == mapY) l.changeBehaviourTo(selectedBehaviour);
        }
    }

    public void changeSelectedBehaviour(LemmingBehaviourEnum blockTypeEnum)
    {
        selectedBehaviour = blockTypeEnum;
    }

    public LemmingBehaviourEnum getSelectedBehaviour()
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

    public void deleteLemming(ArrayList<Lemming> lemmingsToDelete)
    {
        for (Lemming l : lemmingsToDelete)
        {
            deleteLemming(l);
        }
    }
}
