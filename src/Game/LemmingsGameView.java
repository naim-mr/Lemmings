package Game;

import Entity.Block;
import Entity.BlockType.BlockTypeEnum;
import Entity.Entity;
import Entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class LemmingsGameView extends JComponent
{
    public static final int WINDOW_DIMENSION = 600;
    public static final int GAME_DIMENSION = 500;
    public static final int TILE_SIZE = GAME_DIMENSION / LemmingsGame.MAP_DIMENSION;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final LemmingsGame game;

    public LemmingsGameView(LemmingsGame game)
    {
        super();
        this.game = game;

        setOpaque(true);
        setSize(WINDOW_DIMENSION, WINDOW_DIMENSION);

        MouseAdapter clickListener = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent click)
            {
                super.mouseClicked(click);
                int[] tab = windowToMapCoords(click.getX(), click.getY());

            }
            
        };
        addMouseListener(clickListener);

    }

    public static int[] mapToWindowCoords(int x, int y)
    {
        return new int[]{x * TILE_SIZE, TILE_SIZE * y};
    }

    protected static int[] windowToMapCoords(int winX, int winY)
    {
        int mapX = 0;
        int mapY = 0;
        boolean condY = true;
        boolean condX = true;
        int k = 0;
        int j = 0;
        int DIM = LemmingsGame.MAP_DIMENSION;
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
            condX = false;
        }
        if (condY && winY <= j * quotient)
        {
            mapY = j;
            condY = false;
        }

        return new int[]{mapX, mapY};

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        whiteBackground(g);
        drawFooterMenu(g);
        drawEntities(g);
    }

    // Fonciton utilis� pour FACTORISABLE
    private void whiteBackground(Graphics g)
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

    private void drawEntity(Graphics g, Entity e)
    {
        int[] windowCoords = mapToWindowCoords(e.getX(), e.getY());
        e.draw(g, windowCoords[0], windowCoords[1]);
    }

    private void drawEntities(Graphics g)
    {
        ArrayList<Entity> entities = new ArrayList<>();
        entities.addAll(game.getBlocks());
        entities.addAll(game.getLemmings());

        for (Entity e : entities)
        {	
        	if(e!=null)         drawEntity(g, e); // TODO GERER LES PB DE NULL 
        }
    }

    // On fait une fonction qui dessine le menu du bas ou non ? autre solution mettre dans l'Array
    //Faire un objet Array ? 
    private void drawFooterMenu(Graphics g)
    {
        g.setColor(Color.GRAY);
        g.fillRect(0, 500, 600, 100);
    }

}

