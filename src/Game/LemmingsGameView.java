package Game;

import Entity.Entity;
import Entity.LemmingBehaviour.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class LemmingsGameView extends JComponent
{
    private final LemmingsGame game;
    private final LemmingsController controller;
    private static final long serialVersionUID = 1L;
    public static final int WINDOW_DIMENSION = 600;
    public static final int GAME_DIMENSION = 500;
    public static final int TILE_SIZE = GAME_DIMENSION / LemmingsGame.MAP_DIMENSION;

    public LemmingsGameView(LemmingsGame game, LemmingsController controller)
    {
        super();
        this.game = game;
        this.controller = controller;

        setOpaque(true);
        setSize(GAME_DIMENSION, WINDOW_DIMENSION);

        MouseAdapter clickListener = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent click)
            {
                super.mouseClicked(click);
                int[] tab = windowToMapCoords(click.getX(), click.getY());
                controller.onClick(tab[0], tab[1]);
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

    // TODO :  C'est vraiment nul. trouver une meilleure implémentation, refactoriser..............
    // après, c'est de l'affichage, on ne peut pas faire vraiment mieux...
    private void drawFooterMenu(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, GAME_DIMENSION, WINDOW_DIMENSION, WINDOW_DIMENSION - GAME_DIMENSION);
        g.setColor(Color.RED);
        g.drawRect(0, GAME_DIMENSION, WINDOW_DIMENSION - 18, WINDOW_DIMENSION - GAME_DIMENSION - 1);

        // Blocs
        g.setColor(Basher.color);
        g.drawString("Basher", (TILE_SIZE * 1) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 1, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(Blocker.color);
        g.drawString("Blocker", (TILE_SIZE * 3) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 3, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(Bomber.color);
        g.drawString("Bomber", (TILE_SIZE * 5) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 5, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(Climber.color);
        g.drawString("Climber", (TILE_SIZE * 7) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 7, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(Digger.color);
        g.drawString("Digger", (TILE_SIZE * 9) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 9, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(Floater.color);
        g.drawString("Floater", (TILE_SIZE * 11) - 5, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 11, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(Carpenter.color);
        g.drawString("Carpenter", (TILE_SIZE * 13) - 8, GAME_DIMENSION + (TILE_SIZE - 5));
        g.fillRect(TILE_SIZE * 13, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Selection actuelle

        g.setColor(Color.WHITE);
        g.drawString("Selection actuelle", (TILE_SIZE * 15), GAME_DIMENSION + (TILE_SIZE - 5));

        // TODO : trouver mieux que ca, mettre les couleurs dans les comportements
        switch (game.getSelectedBehaviour())
        {
            case BASHER:
                g.setColor(Basher.color);
                break;
            case BLOCKER:
                g.setColor(Blocker.color);
                break;
            case BOMBER:
                g.setColor(Bomber.color);
                break;
            case CARPENTER:
                g.setColor(Carpenter.color);
                break;
            case CLIMBER:
                g.setColor(Climber.color);
                break;
            case DIGGER:
                g.setColor(Digger.color);
                break;
            case FLOATER:
                g.setColor(Floater.color);
                break;
            case NORMAL:
                g.setColor(Normal.color);
                break;
        }

        g.fillRect(TILE_SIZE * 17, GAME_DIMENSION + TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}

