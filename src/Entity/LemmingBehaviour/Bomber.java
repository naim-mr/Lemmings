package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.Lemming;

import java.awt.*;
import java.util.ArrayList;

public class Bomber implements LemmingBehaviour
{
    private final Lemming lemming;

    public Bomber(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {

    }

	@Override
	public boolean update(ArrayList<Block> blocks, ArrayList<Lemming> lemmings) {
		return true ;
		
	}
}
