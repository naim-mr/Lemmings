package Game;

import Entity.*;
import Entity.BlockType.BlockTypeEnum;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import java.util.ArrayList;

public class LemmingsGame 
{
    private final ArrayList<BlockObservable> blocks;
    private final ArrayList<LemmingObservable> lemmings;
    
    

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
    public ArrayList<BlockObservable> getBlocks ()
    {
        return new ArrayList<>(blocks);
    }

    // NE PAS MODIFIER
    public ArrayList<LemmingObservable> getLemmings ()
    {
        return new ArrayList<>(lemmings);
    }

    public ArrayList<BlockObservable> getBlocks (BlockCondition blockCondition)
    {
        ArrayList<BlockObservable> outBlock = new ArrayList<>();
        for (BlockObservable b : blocks)
        {
            if (blockCondition.test(b)) outBlock.add(b);
        }
        return outBlock;
    }

    public ArrayList<LemmingObservable> getLemmings (LemmingCondition lemmingCondition)
    {
        ArrayList<LemmingObservable> outLemming = new ArrayList<>();
        for (LemmingObservable l : lemmings)
        {
            if (lemmingCondition.test(l)) outLemming.add(l);
        }
        return outLemming;
    }



    /* Liste des fonctions à mettre sur la map :
    1 : plusieurs entrées générants des lemmings.   done
    2 : plusieurs sorties retirant des lemmings.
    3 : des lemmings marcheurs qui avancent, montent une case, et font demi-tour fasse à un obstacle de hauteur  2. DONE
    4 : des lemmings marcheurs qui tombent de faible hauteur et survivent. DONE
    5 : des lemmings marcheurs qui tombent de forte hauteur et meurent. DONE
    6 : un bloqueur face auquel les autres lemmings font demi-tour. DONE
    7 : un tunnelier qui creuse devant lui jusqu'à l'air libre. DONE
    8 : un foreur qui creuse sous ses pieds durant 5 pas. DONE
    9 : un bombeur qui explose des obstacles sur un rayon de 2 cases. done
    10 : un charpentier construisant un escalier de 5 marches. DONE
    11 : un grimpeur escalant un obstacle de taille au moins 2. DONE
    12 : un parachutiste qui tombent de hauteur au moins 5 à vitesse 1/2 et survie à sa chute.
    13 : des lemmings arrivant sur un teleporteur qui sont téléportés à un endroit spécifique. DONE
    14 : des lemmings arrivant sur de la lave qui meurent DONE
    15 : un obstacle spécial faisant apparaitre d'autres obstacle à sa destruction. DONE
    16 : un obstacle spécial explosant les lemmings autour de lui à sa destruction. DONE

     */
    public void CreateTestMap ()
    {
        for (int i = 0; i < 20; ++i)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 19));
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 0));
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, i));
        }

        for (int i = 0; i < 19; i++)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 19, i));
        }

        // FONCTION 3 & 11 & 13 & 14: en haut à gauche
        BlockObservable spawner1 = new BlockObservable(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 4);
        spawner1.setOptionalArgs(1);
        blocks.add(spawner1);

        for (int j = 1; j < 6; j++)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, j, 5));
        }

        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 4, 4));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 4));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 3));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 2));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, 2));
        BlockObservable teleporter = new BlockObservable(this, BlockTypeEnum.TELEPORTER_BLOCK, 7, 2);
        teleporter.setOptionalArgs(9, 1);
        blocks.add(teleporter);
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 2));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 1));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 9, 2));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 10, 2));
        blocks.add(new BlockObservable(this, BlockTypeEnum.LAVA_BLOCK, 11, 2));


        // FONCTION 4 & 5 : en haut à droite
       BlockObservable spawner2 = new BlockObservable(this, BlockTypeEnum.SPAWNER_BLOCK, 15, 1);

        spawner2.setOptionalArgs(1);
        blocks.add(spawner2);


        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 16, 2));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 18, 13));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 15, 2));
        for (int i = 2; i < 10; ++i)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 17, i));
        }
        blocks.add(new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 18, 2));
        blocks.add(new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 18, 6));

        // Fonction 10 & 2 : tout en bas
       BlockObservable spawner10 = new BlockObservable(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 18);
        blocks.add(spawner10);
        spawner10.setOptionalArgs(30);
        blocks.add(new BlockObservable(this, BlockTypeEnum.EXIT_BLOCK, 18, 19));

        // FONCTION 6, 7, 8 ; en haut, en dessous teleporter à lave.
       BlockObservable spawner3 = new BlockObservable(this, BlockTypeEnum.SPAWNER_BLOCK, 6, 3);
        spawner3.setOptionalArgs(3);
        blocks.add(spawner3);

        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, 4));
        for (int j = 4; j < 10; j++)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 7, j));
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, j));
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, j));
        }
        for (int i = 0; i < 9; i++)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8 + i, 4));
        }
        for (int i = 0; i < 4; i++)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 12 + i, 3));
        }

        // FONCTION 15 : en dessous de 6, 7, 8

        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, 10));

        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, 11));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 7, 11));
        blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 11));

        BlockObservable bridgeSpawner = new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_SPAWNER, 8, 10);
        ArrayList<BlockObservable> bridge = new ArrayList<>();
        for (int i = 9; i < 18; i++)
        {
            bridge.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 11));
        }
        bridgeSpawner.setOptionalArgs(bridge);

        blocks.add(bridgeSpawner);


        // FONCTION 16 : Juste au dessus du pont qui spawn

        for (int i = 9; i < 17; i++)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 9));
        }
        blocks.add(new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_LANDMINE, 14, 8));
       BlockObservable spawner16 = new BlockObservable(this, BlockTypeEnum.SPAWNER_BLOCK, 9, 8);
        spawner16.setOptionalArgs(5);
        blocks.add(spawner16);

        // Fonction 9 : en dessous d'en haut à gauche.
        for (int i = 1; i < 5; i++)
        {
            blocks.add(new BlockObservable(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 11));
        }

        for (int i = 1; i < 6; i++)
        {

            blocks.add(new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, i, 9));
            blocks.add(new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, i, 8));
        }
        blocks.add(new BlockObservable(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 4, 10));
        blocks.add(new BlockObservable(this, BlockTypeEnum.EXIT_BLOCK, 5, 11));
       BlockObservable spawner9 = new BlockObservable(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 10);
        spawner9.setOptionalArgs(1);
        blocks.add(spawner9);

       BlockObservable spawner9bis = new BlockObservable(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 8);
        spawner9bis.setOptionalArgs(1);
        blocks.add(spawner9bis);


    }

    
    private void update ()
    {
     

        for(LemmingObservable l : lemmings) l.update();
        for(BlockObservable b: blocks)b.update();
        lemmings.removeIf(EntityObservable::getToDelete);
        blocks.removeIf(EntityObservable::getToDelete);
    }

    // Main loop
    /*FONCTION QUI VA GERER La boucle de jeu*/
    @SuppressWarnings("InfiniteLoopStatement")
    public void gameLoop ()
    {  
    	int FRAME_PER_SECOND = 1 ;
        while (true)
        {
            try
            {
                Thread.sleep(1000/FRAME_PER_SECOND);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            update();
      
        }
    }

    public void spawnLemming (Block atBlock, LemmingBehaviourEnum lemmingBehaviour)
    {
        lemmings.add(new LemmingObservable(this, lemmingBehaviour, atBlock.getX(), atBlock.getY()));
    }

    public void changeLemming (int mapX, int mapY)
    {
        for (LemmingObservable l : getLemmings())
        {
            if (l.getX() == mapX && l.getY() == mapY)
            {
                l.changeBehaviourTo(selectedBehaviour);
                break;
            }
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

    public boolean deleteBlock (BlockObservable b)
    {
        if (b != null) {
        	      	return b.destroy();
        }
        else return false;
    }

    public void deleteLemming (LemmingObservable l)
    {
        if (l != null) l.setToDelete();
    }

    public void deleteBlock (ArrayList<BlockObservable> blockArrayList)
    {
        for (BlockObservable b : blockArrayList)
        {
            deleteBlock(b);
        }
    }

    public void deleteLemming (ArrayList<LemmingObservable> lemmingsToDelete)
    {
        for (LemmingObservable l : lemmingsToDelete)
        {
            deleteLemming(l);
        }
    }

    public void setLemmingLocation (LemmingObservable l, int x, int y)
    {
        if (l != null)
        {
            l.setX(x);
            l.setY(y);
        }
    }

    public void setLemmingLocation (ArrayList<LemmingObservable> lemmings, int x, int y)
    {
        for (LemmingObservable l : lemmings)
        {
            setLemmingLocation(l, x, y);
        }
    }

    public void onLemmingEscape (LemmingObservable l)
    {
        ++escapedLemmings;
        deleteLemming(l);
    }

    public void onLemmingEscape (ArrayList<LemmingObservable> lemmings)
    {
        for (LemmingObservable l : lemmings)
        {
            onLemmingEscape(l);
        }
    }

    public void createBlock (BlockTypeEnum blockTypeEnum, int x, int y)
    {
        blocks.add(new BlockObservable(this, blockTypeEnum, x, y));
    }

    public void createBlock (ArrayList<BlockObservable> b)
    {
        blocks.addAll(b);
    }
}
