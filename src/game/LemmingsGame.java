package game;




import java.util.ArrayList;

public class LemmingsGame
{
    public static final int MAP_DIMENSION = 20;
    
    
    private final ArrayList<Block> blocks;
    private final ArrayList<Lemming> lemmings;
   

    public LemmingsGame()
    {
        blocks = new ArrayList<Block>();
        lemmings = new ArrayList<Lemming>();
    }

    // TEST
    /* Séparation des block et leemmings */
    
    public ArrayList<Block> getBlocks()
    {
        return blocks;
    }
    
    public ArrayList<Lemming> getLemmings()
    {
        return lemmings;
    }
    
}
