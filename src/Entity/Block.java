package Entity;

public abstract class Block extends Entity
{
    //Pas besoin ->>> Color c , on l'a d�j� de Entity
    
        
    // TODO : Factory method.
    public Block(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        
        
    }
    
    
    




}
