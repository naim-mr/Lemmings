package Game;


import Entity.Block;
import Entity.DirectionEnum;
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

        blocks.add(new Block(BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, 2));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 2, 2));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 3, 2));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 4, 2));

        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 1, 3));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 2, 3));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 3, 3));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 0, 5));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 1, 5));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 2, 5));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 3, 5));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 3, 5));
        blocks.add(new Block(BlockTypeEnum.DESTRUCTIBLE_BLOCK_GROUND, 10, 2));
        
        //blocks.add(spawner);
        //blocks.add(exit);

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
        ArrayList<Block> blocksDeleted = new ArrayList<>();
        ArrayList<Lemming> lemmingsDeleted = new ArrayList<>();
        for (Lemming l : getLemmings())
        {	
        	
            l.update(blocks,lemmings);   
        }
        for(Lemming l : lemmings) if(l!=null && l.toDelete()) lemmingsDeleted.add(l);
        // TODO gerer les prob de null
        for(Block b : blocks) {
        	
        	if (b!=null && b.toDelete())blocksDeleted.add(b);
        	//TODO GERER LE PB DE NULL
        	
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
        spawnLemming(spawner, LemmingBehaviourEnum.BASHER);
        while (!gameOver)
        { 	
        	System.out.println(k);
        	if(k==4) lemmings.get(0).changeBehaviourTo(LemmingBehaviourEnum.NORMAL);
        	if(k==6) lemmings.get(0).changeBehaviourTo(LemmingBehaviourEnum.BASHER);
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
