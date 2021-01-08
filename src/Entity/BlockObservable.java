package Entity;

import java.util.ArrayList;

import Entity.BlockType.BlockTypeEnum;
import Game.LemmingsGame;

public class BlockObservable extends EntityObservable {

	public BlockObservable(LemmingsGame game, BlockTypeEnum blockType, int x, int y) {
		super(null);
		entity=new Block(game,blockType,x,y);
		observers =new ArrayList<>();
	
	}
	
	public BlockObservable(Block b) {
		super(null);
		entity= b;
		observers=new ArrayList<>();
	}
	
	public static BlockObservable blockObservable(LemmingsGame game, BlockTypeEnum blockType, int x, int y) {
			return new BlockObservable(game,blockType,x,y);
	}
	
    public boolean destroy ()
    {
        return ((Block) entity).destroy();
    }

    public ArrayList<LemmingObservable> getLemmingsOnBlock ()
    {
        return ((Block) entity).getLemmingsOnBlock();
    }

    public void setOptionalArgs (int... args)
    {
    	((Block) entity).setOptionalArgs(args);
    }

    public void setOptionalArgs (ArrayList<BlockObservable> blocks)
    {
    	((Block) entity).setOptionalArgs(blocks);
    }

    public boolean findSuperiorBlock ()
    {
    	return ((Block) entity).findSuperiorBlock();
    }
	
    @Override 
    public boolean equals(Object obj ) {
    	if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockObservable other = (BlockObservable) obj;
		if(this.entity== other.entity) return true; 
		return false;
    }
}
