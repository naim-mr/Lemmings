package Entity.LemmingBehaviour;

import Entity.Block;
import Entity.Lemming;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;

public class Blocker implements LemmingBehaviour
{	
	public static final Color color = Color.CYAN;
	private final Lemming lemming;

    public Blocker(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(color);
        graphics.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }

 
	@Override
	public boolean update () {
		return true;
	}
}
