package Entity.LemmingBehaviour;

import Entity.DirectionEnum;
import Entity.Lemming;
import Game.LemmingsGameView;

import java.awt.*;
import java.util.ArrayList;


public class Blocker implements LemmingBehaviour
{
    private final Lemming lemming;

    public Blocker(Lemming lemming)
    {
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics graphics, int windowX, int windowY)
    {
        graphics.setColor(Color.CYAN);
        graphics.fillRect(windowX, windowY, LemmingsGameView.TILE_SIZE, LemmingsGameView.TILE_SIZE);
    }

 
	@Override
	public boolean update ()
    {
        changeLemmingDirection(lemming.getSideLemmings());
        return true;
	}

	private void changeLemmingDirection (ArrayList<Lemming> lemmings)
    {
        for (Lemming l : lemmings)
        {
            l.changeDirectionTo((l.getDirection() == DirectionEnum.LEFT) ? DirectionEnum.RIGHT : DirectionEnum.LEFT);
        }
    }
}
