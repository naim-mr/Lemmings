package Game;

import Entity.Block;
import Entity.BlockType.*;
import Entity.Entity;
import Entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class LemmingsGameView extends JComponent
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WINDOW_DIMENSION = 600;
    public static final int GAME_DIMENSION = 500;
    private final LemmingsGame game;
    private final Spawner spawner;
    private final Exit exit;

    public LemmingsGameView (LemmingsGame game)
    {
        super();
        this.game = game;
        CreateTestMap(this.game.getBlocks());
		
        this.spawner= new Spawner(0,1);
        this.exit= new Exit(10,20);
        
        
        setOpaque(true);
        setSize(WINDOW_DIMENSION, WINDOW_DIMENSION);
        
        MouseAdapter clickListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				super.mouseClicked(click);
				int[] tab= windowToMapCoords(click.getX(),click.getY());
				
			}			
        }; 					
		addMouseListener(clickListener);
    
    }
    
    
    public void CreateTestMap (ArrayList<Block> blocks){
    
    //    blocks.add(new Indestructible(10,0));
        blocks.add(new Indestructible(100,25));
        blocks.add(new Lava(400,0,600,100));
        
     /*   blocks.add(new Indestructible(10, 11));
        blocks.add(new Indestructible(10, 12));
        blocks.add(new Indestructible(15, 1));
        blocks.add(new Indestructible(15, 2));
        blocks.add(new Indestructible(16, 1));
        blocks.add(new Indestructible(17, 3));
        blocks.add(new Indestructible(18, 8));
        blocks.add(new Indestructible(19, 9));*/
        
    }
    
    
    
    private void leemingsMoove() {
    	ArrayList<Integer> index= new ArrayList<>();
		ArrayList<Lemming> lemmings =game.getLemmings();
		   	for(Lemming l :  lemmings ) {
				
		   			boolean alive= l.tick(game.getBlocks());
				if(!alive) {
					index.add(lemmings.indexOf(l));
					
				}
				
				
		}
		   for(Integer i: index) {
			   
			   lemmings.remove(i.intValue());
		   }
    }
    
    
    /*FONCTION QUI VA GERER La boucle de jeu*/
    public void play() {
       	boolean gameOver=false;
       	int k=0;
       	spawner.spawn(game.getLemmings());
    	while(!gameOver) {
    	   if(k==200) {
    		   spawner.spawn(game.getLemmings());
 			   k=0;
 		   }
    	   //A modifier: sans sleep 
    	   try {
				Thread.sleep((10));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	   leemingsMoove();
 		   repaint();
 		   k++;
 		      			
    		
    	}
    }
    
     @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        whiteBackground(g);
        spawner.draw(g,spawner.getX(),spawner.getY());
        exit.draw(g,exit.getX(),exit.getY());
        drawFooterMenu(g);
        drawEntities(g);
        
        
        
    }
    
        
        
    // Pas besoin de faire gameTileSize*(LeemingsGame.Map_dimension-y-1) 
    // On a juste consid�r� l'origine du rep�re comme le sommmet en haut � gauche 
    // Je te laisse faire le test
    
    
    public static int [] mapToWindowCoords(int x, int y)
    {
        int gameTileSize = GAME_DIMENSION / LemmingsGame.MAP_DIMENSION;
        return new int [] {x*gameTileSize, gameTileSize* y};
        
    }
        
       

    protected static int[] windowToMapCoords(int winX, int winY) 
        {
    		int mapX=0;
    		int mapY = 0 ; 
    		boolean condY=true;
    		boolean condX=true;
    		int k=0;
    		int j=0;
    		int DIM =LemmingsGame.MAP_DIMENSION;
    		int quotient =  WINDOW_DIMENSION / DIM;
        	
    		while((condX || condY) && ( k<DIM && j<DIM)) {
    			if (condX && winX< k*quotient) {
    				mapX=k;
    				condX=false;
    			}
    			if (condY && winY < j*quotient) {
    				mapY=j;
    				condY=false;
    			}
    			k++;
    			j++;				
    		}
    		if (condX && winX <= k*quotient) {
    			mapX=k;
    			condX=false;
    		}
    		if (condY && winY <= j*quotient) {
    			mapY =j;
    			condY=false;
    		}
    		
        	return new int[] {mapX,mapY};
        	
        }
    
    
    // Fonciton utilis� pour FACTORISABLE
    private void whiteBackground(Graphics g ) 
    {
    	int entityDimension =  WINDOW_DIMENSION / LemmingsGame.MAP_DIMENSION;
    	g.setColor(Color.WHITE);
    	int [] windowCoords;
    	for(int i=0;i<=GAME_DIMENSION;i++) {
    		for(int j=0;j<=GAME_DIMENSION;j++){
    			  windowCoords = mapToWindowCoords(i, j);
    			  g.fillRect(windowCoords[0],windowCoords[1], entityDimension, entityDimension);
    		      g.drawRect(windowCoords[0],windowCoords[1], entityDimension, entityDimension);
    		}
    	}
    }
    
    private void drawEntity (Graphics g, Entity e)
    {
        int entityDimension =  WINDOW_DIMENSION / LemmingsGame.MAP_DIMENSION;
       
        int [] windowCoords = mapToWindowCoords(e.getX(), e.getY());
       //        e.draw(g,windowCoords[0] ,windowCoords[1]);
        e.draw(g,e.getY() ,e.getX());
      //g.drawRect(windowCoords[0],windowCoords[1], entityDimension, entityDimension); UTILE OU NON ?la m�me que fill? 
        
    }

    private void drawEntities (Graphics g)
    {
    	ArrayList<Entity> entities = new ArrayList<>();
    	entities.addAll(game.getBlocks());
    	entities.addAll(game.getLemmings());
        for (Entity e : entities)
        {
            drawEntity(g, e);
        }
    }
    
    
    // On fait une fonction qui dessine le menu du bas ou non ? autre solution mettre dans l'Array
    //Faire un objet Array ? 
    private void  drawFooterMenu(Graphics g) {
    	g.setColor(Color.GRAY);
    	g.fillRect(0,500, 600, 100);
    } 	
    	
  }

