package Entity.BlockType;

import Entity.Block;
import Entity.Lemming;

import java.awt.*;
import java.util.ArrayList;

public class Destructible implements BlockType
{
	private Block block;
	private DestructibleTypeEnum type ;
	private Block hiddenBlock;// � g�rer mieux que �a surement via h�ritage
	
	
	public Destructible(Block block,DestructibleTypeEnum type,Block hiddenBlock )
	{	
		
		this.hiddenBlock=hiddenBlock;
		this.type=type;
		this.block = block;
		
	}

	public Destructible(Block block,DestructibleTypeEnum type )
	{
		this.hiddenBlock=null;
		this.type=type;
		this.block = block;
		
	}
	@Override
	public void draw(Graphics graphics, int windowX, int windowY) {
		graphics.setColor(Color.GREEN);
		graphics.fillRect(windowX, windowY, block.getWidth(), block.getHeight());
		
	}

	

	@Override
	public boolean update(ArrayList<Block> blocks, ArrayList<Lemming> lemmings) {
		// En passe � delete. NB�� : si update est appel� c'est uniquement lorsque qu'il faut le d�truire pas besoin sinon
		switch(type) {
			case GROUND:
					block.delete();
					break;
			case LANDMINE:
					System.out.println("deleted");
					block.delete();
					blast(blocks,lemmings);
					break;
			case HIDDEN_BLOCK:
					block.delete();
					blocks.add(this.hiddenBlock);
					break;
		}
		return true ; 
	}
	
	private void blast(ArrayList<Block> blocks, ArrayList<Lemming> lemmings) {
			// Explosion en croix des blocks en lemmings ( y'a pas d'explosion encore lol juste une disparition
			for(Block b: blocks) {
				System.out.println(b.getX()+" "+b.getY()+" "+ b.toDelete());	
				if((b.getX()== block.getX()+1 && b.getY()==block.getY())||(b.getX()== block.getX()-1 && b.getY()==block.getY())) b.delete();	
				if((b.getY()== block.getY()+1 && b.getX()==block.getX())||(b.getY()== block.getY()-1&& b.getX()==block.getX())) b.delete();
			}
			for(Lemming l : lemmings) {
				if((l.getX()== block.getX()+1 && l.getY()==block.getY())||(l.getX()== block.getX()-1 && l.getY()==block.getY())) l.delete();	
				if((l.getY()== block.getY()+1 && l.getX()==block.getX())||(l.getY()== block.getY()-1 && l.getY()==block.getX())) l.delete();
			}
		
	}	
}