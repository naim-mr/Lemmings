package Game;

import Entity.Block;
import Entity.BlockType.BlockTypeEnum;
import Entity.Lemming;
import Entity.LemmingBehaviour.LemmingBehaviourEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import static Entity.BlockType.BlockTypeEnum.*;


public class LemmingsGameView extends JComponent implements ILemmingsGameObserver
{
    public static final int WINDOW_DIMENSION = 600;
    public static final int GAME_DIMENSION = 500;
    public static final int TILE_SIZE = GAME_DIMENSION / LemmingsGame.MAP_DIMENSION;
    private static final long serialVersionUID = 1L;
    private static final Map<BlockTypeEnum, Color> blockColor = new HashMap<>();
    private static final Map<LemmingBehaviourEnum, Color> lemmingColor = new HashMap<>();
    private final LemmingsGameObservable game;

    public LemmingsGameView (LemmingsGameObservable game, LemmingsController controller)
    {
        super();
        this.game = game;

        setOpaque(true);
        setSize(GAME_DIMENSION, WINDOW_DIMENSION);

        MouseAdapter clickListener = new MouseAdapter()
        {
            @Override
            public void mouseClicked (MouseEvent click)
            {
                super.mouseClicked(click);
                int[] tab = windowToMapCoords(click.getX(), click.getY());
                controller.onClick(tab[0], tab[1]);
            }

        };
        addMouseListener(clickListener);
        initializeEntityColors();
    }

    public static void initializeEntityColors ()
    {
        blockColor.put(DESTRUCTIBLE_BLOCK, Color.GREEN);
        blockColor.put(DESTRUCTIBLE_BLOCK_LANDMINE, new Color(120, 0, 70));
        blockColor.put(DESTRUCTIBLE_BLOCK_SPAWNER, new Color(31, 74, 1));
        blockColor.put(EXIT_BLOCK, new Color(128, 128, 0));
        blockColor.put(INDESTRUCTIBLE_BLOCK, Color.BLACK);
        blockColor.put(LAVA_BLOCK, Color.RED);
        blockColor.put(SPAWNER_BLOCK, Color.YELLOW);
        blockColor.put(TELEPORTER_BLOCK, new Color(128, 0, 255));

        lemmingColor.put(LemmingBehaviourEnum.BASHER, Color.RED);
        lemmingColor.put(LemmingBehaviourEnum.BLOCKER, Color.CYAN);
        lemmingColor.put(LemmingBehaviourEnum.BOMBER, Color.MAGENTA);
        lemmingColor.put(LemmingBehaviourEnum.CARPENTER, new Color(160, 82, 45));
        lemmingColor.put(LemmingBehaviourEnum.CLIMBER, Color.LIGHT_GRAY);
        lemmingColor.put(LemmingBehaviourEnum.DIGGER, Color.YELLOW);
        lemmingColor.put(LemmingBehaviourEnum.FLOATER, Color.GRAY);
        lemmingColor.put(LemmingBehaviourEnum.NORMAL, Color.PINK);
    }

    public static int[] mapToWindowCoords (int x, int y)
    {
        return new int[]{x * TILE_SIZE, TILE_SIZE * y};
    }

    protected static int[] windowToMapCoords (int winX, int winY)
    {
        int mapX = 0;
        int mapY = 0;
        boolean condY = true;
        boolean condX = true;
        int k = 0;
        int j = 0;
        int DIM = LemmingsGame.MAP_DIMENSION + (WINDOW_DIMENSION - GAME_DIMENSION) / TILE_SIZE;
        int quotient = WINDOW_DIMENSION / DIM;

        while ((condX || condY) && (k < DIM && j < DIM))
        {
            if (condX && winX < k * quotient)
            {
                mapX = k;
                condX = false;
            }
            if (condY && winY < j * quotient)
            {
                mapY = j;
                condY = false;
            }
            k++;
            j++;
        }
        if (condX && winX <= k * quotient)
        {
            mapX = k;
        }
        if (condY && winY <= j * quotient)
        {
            mapY = j;
        }
        return new int[]{mapX - 1, mapY - 1};
    }

    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        whiteBackground(g);
        drawFooterMenu(g);
        drawEntities(g);
    }

    // Fonciton utilis� pour FACTORISABLE
    private void whiteBackground (Graphics g)
    {
        int entityDimension = WINDOW_DIMENSION / LemmingsGame.MAP_DIMENSION;
        g.setColor(Color.WHITE);
        int[] windowCoords;
        for (int i = 0; i <= GAME_DIMENSION; i++)
        {
            for (int j = 0; j <= GAME_DIMENSION; j++)
            {
                windowCoords = mapToWindowCoords(i, j);
                g.fillRect(windowCoords[0], windowCoords[1], entityDimension, entityDimension);
                g.drawRect(windowCoords[0], windowCoords[1], entityDimension, entityDimension);
            }
        }
    }

    private void drawBlock (Graphics g, Block b)
    {
        int[] windowCoords = mapToWindowCoords(b.getX(), b.getY());
        g.setColor(blockColor.get(b.getType()));
        g.fillRect(windowCoords[0], windowCoords[1], TILE_SIZE, TILE_SIZE);
    }

    private void drawLemming (Graphics g, Lemming l)
    {
        int[] windowCoords = mapToWindowCoords(l.getX(), l.getY());
        g.setColor(lemmingColor.get(l.getType()));
        g.fillRect(windowCoords[0], windowCoords[1], TILE_SIZE, TILE_SIZE);
    }

    private void drawEntities (Graphics g)
    {
        for (Block b : game.getBlocks())
        {
            drawBlock(g, b);
        }

        for (Lemming l : game.getLemmings())
        {
            drawLemming(g, l);
        }
    }

    // TODO
    private void drawFooterMenu (Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, GAME_DIMENSION, WINDOW_DIMENSION, WINDOW_DIMENSION - GAME_DIMENSION);
        g.setColor(Color.RED);
        g.drawRect(0, GAME_DIMENSION, WINDOW_DIMENSION - 18, WINDOW_DIMENSION - GAME_DIMENSION - 1);

        // Blocs
        g.setColor(lemmingColor.get(LemmingBehaviourEnum.BASHER));
        g.drawString("Basher", (TILE_SIZE) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(lemmingColor.get(LemmingBehaviourEnum.BLOCKER));
        g.drawString("Blocker", (TILE_SIZE * 3) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 3, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(lemmingColor.get(LemmingBehaviourEnum.BOMBER));
        g.drawString("Bomber", (TILE_SIZE * 5) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 5, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(lemmingColor.get(LemmingBehaviourEnum.CLIMBER));
        g.drawString("Climber", (TILE_SIZE * 7) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 7, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(lemmingColor.get(LemmingBehaviourEnum.DIGGER));
        g.drawString("Digger", (TILE_SIZE * 9) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 9, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(lemmingColor.get(LemmingBehaviourEnum.FLOATER));
        g.drawString("Floater", (TILE_SIZE * 11) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 11, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(lemmingColor.get(LemmingBehaviourEnum.CARPENTER));
        g.drawString("Carpenter", (TILE_SIZE * 13) - 8, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 13, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Selection actuelle

        g.setColor(Color.WHITE);
        g.drawString("Selection actuelle", (TILE_SIZE * 15), GAME_DIMENSION + (TILE_SIZE - 5));


        g.setColor(lemmingColor.get(game.getSelectedBehaviour()));
        g.fillRect(TILE_SIZE * 17, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public void update ()
    {
        repaint();
    }
}

