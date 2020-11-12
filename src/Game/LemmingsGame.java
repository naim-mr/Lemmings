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


    /* Liste des fonctions à mettre sur la map :
    1 : plusieurs entrées générants des lemmings.   done
    2 : plusieurs sorties retirant des lemmings.
    3 : des lemmings marcheurs qui avancent, montent une case, et font demi-tour fasse à un obstacle de hauteur  2. DONE
    4 : des lemmings marcheurs qui tombent de faible hauteur et survivent. DONE
    5 : des lemmings marcheurs qui tombent de forte hauteur et meurent. DONE
    6 : un bloqueur face auquel les autres lemmings font demi-tour. DONE
    7 : un tunnelier qui creuse devant lui jusqu'à l'air libre. DONE
    8 : un foreur qui creuse sous ses pieds durant 5 pas. DONE
    9 : un bombeur qui explose des obstacles sur un rayon de 2 cases.
    10 : un charpentier construisant un escalier de 5 marches. DONE
    11 : un grimpeur escalant un obstacle de taille au moins 2. DONE
    12 : un parachutiste qui tombent de hauteur au moins 5 à vitesse 1/2 et survie à sa chute.
    13 : des lemmings arrivant sur un teleporteur qui sont téléportés à un endroit spécifique. DONE
    14 : des lemmings arrivant sur de la lave qui meurent DONE
    15 : un obstacle spécial faisant apparaitre d'autres obstacle à sa destruction. DONE
    16 : un obstacle spécial explosant les lemmings autour de lui à sa destruction.

     */
    public void CreateTestMap ()
    {
        for (int i = 0; i < 20; ++i)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 19));
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 0));
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 19, i));
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, i));
        }

        // FONCTION 3 & 11 & 13 & 14: en haut à gauche
        Block spawner1 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 4);
        spawner1.setOptionalArgs(1);
        blocks.add(spawner1);

        for (int j = 1; j < 6; j++)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, j, 5));
        }

        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 4, 4));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 4));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 3));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, 2));
        Block teleporter = new Block(this, BlockTypeEnum.TELEPORTER_BLOCK, 7, 2);
        teleporter.setOptionalArgs(9, 1);
        blocks.add(teleporter);
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 1));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 9, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 10, 2));
        blocks.add(new Block(this, BlockTypeEnum.LAVA_BLOCK, 11, 2));


        // FONCTION 4 & 5 : en haut à droite
        Block spawner2 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 15, 1);

        spawner2.setOptionalArgs(1);
        blocks.add(spawner2);


        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 16, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 18, 13));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 15, 2));
        for (int i = 2; i < 7; ++i)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 17, i));
        }
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 18, 2));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 18, 6));


        // FONCTION 6, 7, 8 ; en haut, en dessous teleporter à lave.
        Block spawner3 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 6, 3);
        spawner3.setOptionalArgs(3);
        blocks.add(spawner3);

        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, 4));
        for (int j = 4; j < 10; j++)
        {
            blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 7, j));
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, j));
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, j));
        }
        for (int i = 0; i < 9; i++)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8 + i, 4));
        }
        for (int i = 0; i < 4; i++)
        {
            blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 12 + i, 3));
        }

        // FONCTION 15 : en dessous de 6, 7, 8

        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, 10));

        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 6, 11));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 7, 11));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 11));

        Block bridgeSpawner = new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_SPAWNER, 8, 10);
        ArrayList<Block> bridge = new ArrayList<Block>();
        for (int i = 9; i < 18; i++)
        {
            bridge.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 11));
        }
        bridgeSpawner.setOptionalArgs(bridge);

        blocks.add(bridgeSpawner);

        // TEST

        Block spawner4 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 18);
        spawner4.setOptionalArgs(1);
        blocks.add(spawner4);
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

    public void createBlock (BlockTypeEnum blockTypeEnum, int x, int y)
    {
        blocks.add(new Block(this, blockTypeEnum, x, y));
    }

    public void createBlock (ArrayList<Block> b)
    {
        blocks.addAll(b);
    }
}
