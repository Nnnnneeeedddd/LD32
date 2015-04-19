package biz.boulter.investigation.collisionExecutors;

import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.Player;
import biz.boulter.investigation.state.GameOver;

public class Accusal implements CollisionExec
{
	private String whichAccusal;
	
	public Accusal(String whichAccusal)
	{
		this.whichAccusal = whichAccusal;
	}
	
	@Override
	public void execute(Player p, Entity collOwner)
	{
		p.game.getMain().setState(new GameOver(whichAccusal));
	}
	
}
