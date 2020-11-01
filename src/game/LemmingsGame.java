package game;




import Entity.Block;
import Entity.Lemming;

import java.util.ArrayList;

public class LemmingsGame
{
    public static final int MAP_DIMENSION = 20;
    
    
    private final ArrayList<Block> blocks;
    private final ArrayList<Lemming> lemmings;
   

    public LemmingsGame()
    {
        blocks = new ArrayList<>();
        lemmings = new ArrayList<>();
    }

    // TEST
    /* S�paration des block et leemmings */
    
    public ArrayList<Block> getBlocks()
    {
        return blocks;
    }
    
    public ArrayList<Lemming> getLemmings()
    {
        return lemmings;
    }
    
}
