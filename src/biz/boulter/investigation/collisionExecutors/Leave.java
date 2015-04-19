package biz.boulter.investigation.collisionExecutors;

import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Util;
import biz.boulter.investigation.entity.Door;
import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.Player;

public class Leave implements CollisionExec
{

	private BufferedImage lockedMessage;
	
	public Leave()
	{
		lockedMessage = Art.getTextImage(new String[]{"Please investigate further", "before leaving..."});
	}
	
	public void execute(Player p, Entity collOwner)
	{
		Door d = Util.getDoor(p);
		if(d.isLocked())
		{
			p.setMessage(lockedMessage);
		}else
		{
			Art.blip.play();
			
			p.game.loadNextLevel();
		}
	}
	
}
