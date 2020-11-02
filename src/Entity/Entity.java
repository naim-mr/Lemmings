package Entity;

public abstract class Entity
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

    // TODO : Doit retourner la position horizontale de l'entité dans la map (et non dans la fenêtre).
    public int getX()
    {
        return x;
    }

    // TODO : Doit retourner la position verticale dans la map
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
