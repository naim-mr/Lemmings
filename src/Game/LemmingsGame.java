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
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 19, i));
            blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 0, i));
        }

        // FONCTION 3
        Block spawner1 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 1, 3);
        spawner1.setOptionalArgs(1);
        blocks.add(spawner1);
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 1, 4));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 2, 4));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 3, 4));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 4, 4));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 4));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 4, 3));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 3));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 5, 1));

        // FONCTION 4
        Block spawner2 = new Block(this, BlockTypeEnum.SPAWNER_BLOCK, 15, 1);
        spawner2.setOptionalArgs(1);

        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 17, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 16, 2));
        blocks.add(new Block(this, BlockTypeEnum.INDESTRUCTIBLE_BLOCK, 15, 2));
        blocks.add(new Block(this, BlockTypeEnum.DESTRUCTIBLE_BLOCK, 18, 2));
        blocks.add(spawner2);
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

    public void createBlock (BlockTypeEnum blockTypeEnum, int x, int y)
    {
        blocks.add(new Block(this, blockTypeEnum, x, y));
    }

    public void createBlock (ArrayList<Block> b)
    {
        blocks.addAll(b);
    }
}
