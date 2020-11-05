package Game;


import Entity.Block;
import Entity.BlockType.BlockTypeEnum;
import Entity.Lemming;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import java.util.ArrayList;

public class LemmingsGame
{
    private final ArrayList<Block> blocks;
    private final ArrayList<Lemming> lemmings;
    private LemmingsGameView lemmingsGameView; // TODO : est-ce qu'il n'y a pas mieux comme implémentation ?
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

    public ArrayList<Block> getBlocks()
    {
        return blocks;
    }

    public ArrayList<Lemming> getLemmings()
    {
        return lemmings;
    }

    public void setLemmingsGameView (LemmingsGameView lemmingsGameView)
    {
        this.lemmingsGameView = lemmingsGameView;
    }

    public void CreateTestMap()
    {
        spawner = new Block(BlockTypeEnum.SPAWNER_BLOCK, 0, 1);
        exit = new Block(BlockTypeEnum.EXIT_BLOCK, 10, 19);

        //    blocks.add(new Indestructible(10,0));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, 0));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 0, 2));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, 4));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 0, 6));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, 8));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 0, 10));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, 12));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 0, 14));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, 16));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 0, 18));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 2, 0));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 4, 4));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 6, 6));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 8));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 10, 10));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 12, 12));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 14, 14));
        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 16, 16));
        blocks.add(new Block(BlockTypeEnum.LAVA_BLOCK, 18, 18));
        blocks.add(spawner);
        blocks.add(exit);

     /*   blocks.add(new Indestructible(10, 11));
        blocks.add(new Indestructible(10, 12));
        blocks.add(new Indestructible(15, 1));
        blocks.add(new Indestructible(15, 2));
        blocks.add(new Indestructible(16, 1));
        blocks.add(new Indestructible(17, 3));
        blocks.add(new Indestructible(18, 8));
        blocks.add(new Indestructible(19, 9));*/

    }

    private void update()
    {
        for (Block b : getBlocks())
        {
            b.update();
        }
        for (Lemming l : getLemmings())
        {
            l.update();
        }
    }

    // Main loop
    /*FONCTION QUI VA GERER La boucle de jeu*/
    public void gameLoop()
    {
        boolean gameOver = false;
        int k = 0;
        spawnLemming(spawner, LemmingBehaviourEnum.NORMAL);
        while (!gameOver)
        {
            if (k == 5)
            {
                spawnLemming(spawner, LemmingBehaviourEnum.NORMAL);
                k = 0;
            }

            try
            {
                Thread.sleep(1500);
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

    private void spawnLemming (Block atBlock, LemmingBehaviourEnum lemmingBehaviour)
    {
        lemmings.add(new Lemming(lemmingBehaviour, atBlock.getX(), atBlock.getY()));
    }

    public void changeLemming (int mapX, int mapY)
    {
        for (Lemming l : getLemmings())
        {
            if (l.getX() == mapX && l.getY() == mapY) l.changeBehaviourTo(selectedBehaviour);
        }
    }

    // Pour le moment, ca change le comportement sans se poser de questions, à voir comment ca doit être réellement fait.
    public void changeSelectedBehaviour (LemmingBehaviourEnum blockTypeEnum)
    {
        selectedBehaviour = blockTypeEnum;
    }

    public LemmingBehaviourEnum getSelectedBehaviour()
    {
        return selectedBehaviour;
    }
}
