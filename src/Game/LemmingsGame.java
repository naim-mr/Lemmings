package Game;

import Entity.*;
import Entity.BlockType.BlockTypeEnum;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import java.util.ArrayList;

public class LemmingsGame implements ILemmingsGame
{
    public final int MAP_DIMENSION = 20;
    private final ArrayList<Block> blocks;
    private final ArrayList<Block> blocksToAdd;
    private final ArrayList<Lemming> lemmings;
    private final ArrayList<Lemming> lemmingsToAdd;
    private LemmingBehaviourEnum selectedBehaviour = LemmingBehaviourEnum.NORMAL;

    LemmingsGame ()
    {
        blocks = new ArrayList<>();
        lemmings = new ArrayList<>();
        blocksToAdd = new ArrayList<>();
        lemmingsToAdd = new ArrayList<>();

        CreateTestMap();
    }

    public ArrayList<Block> getBlocks ()
    {
        return new ArrayList<>(blocks);
    }

    public ArrayList<Lemming> getLemmings ()
    {
        return new ArrayList<>(lemmings);
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

    /* Liste des fonctions à mettre sur la map :
    1 : plusieurs entrées générants des lemmings.
    2 : plusieurs sorties retirant des lemmings.
    3 : des lemmings marcheurs qui avancent, montent une case, et font demi-tour fasse à un obstacle de hauteur  2.
    4 : des lemmings marcheurs qui tombent de faible hauteur et survivent.
    5 : des lemmings marcheurs qui tombent de forte hauteur et meurent.
    6 : un bloqueur face auquel les autres lemmings font demi-tour.
    7 : un tunnelier qui creuse devant lui jusqu'à l'air libre.
    8 : un foreur qui creuse sous ses pieds durant 5 pas.
    9 : un bombeur qui explose des obstacles sur un rayon de 2 cases.
    10 : un charpentier construisant un escalier de 5 marches.
    11 : un grimpeur escalant un obstacle de taille au moins 2.
    12 : un parachutiste qui tombent de hauteur au moins 5 à vitesse 1/2 et survie à sa chute.
    13 : des lemmings arrivant sur un teleporteur qui sont téléportés à un endroit spécifique.
    14 : des lemmings arrivant sur de la lave qui meurent
    15 : un obstacle spécial faisant apparaitre d'autres obstacle à sa destruction.
    16 : un obstacle spécial explosant les lemmings autour de lui à sa destruction.

     */
    public void CreateTestMap ()
    {
        for (int i = 0; i < 20; ++i)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 19));
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 0));
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, i));
        }

        for (int i = 0; i < 19; i++)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 19, i));
        }

        // FONCTION 3 & 11 & 13 & 14: en haut à gauche
        Block spawner1 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 4);
        spawner1.setNumberToSpawn(1);
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
        teleporter.setTeleportTo(9, 1);
        blocks.add(teleporter);
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 8, 1));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 9, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 10, 2));
        blocks.add(new Block(this, BlockTypeEnum.LAVA_BLOCK, 11, 2));


        // FONCTION 4 & 5 : en haut à droite
        Block spawner2 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 15, 1);

        spawner2.setNumberToSpawn(1);
        blocks.add(spawner2);


        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 16, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 18, 13));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 15, 2));
        for (int i = 2; i < 10; ++i)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 17, i));
        }
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 18, 2));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 18, 6));

        // Fonction 10 & 2 : en bas
        Block spawner10 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 18);
        blocks.add(spawner10);
        spawner10.setNumberToSpawn(30);
        blocks.add(new Block(this, BlockTypeEnum.EXIT_BLOCK, 18, 19));

        // FONCTION 6, 7, 8 ; en haut, en dessous teleporter à lave.
        Block spawner3 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 6, 3);
        spawner3.setNumberToSpawn(3);
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
        ArrayList<Block> bridge = new ArrayList<>();
        for (int i = 9; i < 18; i++)
        {
            bridge.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 11));
        }
        bridgeSpawner.setBlocksToSpawn(bridge);

        blocks.add(bridgeSpawner);


        // FONCTION 16

        for (int i = 9; i < 17; i++)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 9));
        }
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK_LANDMINE, 14, 8));
        Block spawner16 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 9, 8);
        spawner16.setNumberToSpawn(5);
        blocks.add(spawner16);

        // Fonction 9
        for (int i = 1; i < 5; i++)
        {
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, i, 11));
        }

        for (int i = 1; i < 6; i++)
        {

            blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, i, 9));
            blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, i, 8));
        }
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 4, 10));
        blocks.add(new Block(this, BlockTypeEnum.EXIT_BLOCK, 5, 11));
        Block spawner9 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 10);
        spawner9.setNumberToSpawn(1);
        blocks.add(spawner9);

        Block spawner9bis = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 8);
        spawner9bis.setNumberToSpawn(1);
        blocks.add(spawner9bis);
    }

    public void step ()
    {
        for (Lemming l : getLemmings())
        {
            l.update();
        }
        for (Block b : blocks)
        {
            b.update();
        }

        blocks.addAll(blocksToAdd);
        blocksToAdd.clear();

        lemmings.addAll(lemmingsToAdd);
        lemmingsToAdd.clear();

        lemmings.removeIf(Entity::getToDelete);
        blocks.removeIf(Entity::getToDelete);
    }

    public void spawnLemming (Block atBlock, LemmingBehaviourEnum lemmingBehaviour)
    {
        lemmingsToAdd.add(new Lemming(this, lemmingBehaviour, atBlock.getX(), atBlock.getY()));
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
        if (b != null)
        {
            return b.setToDelete();
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
        blocksToAdd.add(new Block(this, blockTypeEnum, x, y));
    }

    public void createBlock (ArrayList<Block> b)
    {
        blocksToAdd.addAll(b);
    }

    public int getMapDimension ()
    {
        return MAP_DIMENSION;
    }
}
