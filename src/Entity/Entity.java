package Entity;

public abstract class Entity implements Drawable
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    
    /* Chaque entit� va avoir une largeur et une hauteur 
     * Car cela nous permettra de checker si un leemings et bien sur un block ou non
     * En fait le probl�me c'est que si on test juste si Lemmings.get().Y .. blablabla
     */
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight(){
    	return height;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
    
    public void setX(int x) {
    	this.x=x;
    }
    public void setY(int y) {
    	this.y=y;
    }

    
     
    
}
