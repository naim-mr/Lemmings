package Entity;

import Entity.LemmingBehaviour.LemmingBehaviourEnum;
import Game.LemmingsGame;

public class LemmingObservable extends EntityObservable {

	public LemmingObservable(LemmingsGame game, LemmingBehaviourEnum lemmingBehaviour, int x, int y) {
		super(new Lemming(game,lemmingBehaviour,x,y));
		// TODO Auto-generated constructor stub
	}
	public LemmingObservable(Lemming l ) {
			super(null);
			entity=l;
	}
	
	 public void changeBehaviourTo (LemmingBehaviourEnum lemmingBehaviour) {
		 ((Lemming)entity).changeBehaviourTo(lemmingBehaviour);
	 }
	public void changeDirectionTo(DirectionEnum d) {
		((Lemming)entity).changeDirectionTo(d);
		// TODO Auto-generated method stub
		
	}
}