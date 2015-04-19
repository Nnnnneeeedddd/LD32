package biz.boulter.investigation.collisionExecutors;

import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Util;
import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.Player;

public class BloodTrail implements CollisionExec
{
	private BufferedImage message;
	private boolean blipped = false;
	
	public BloodTrail()
	{
		message = Art.getTextImage(new String[]{"The killer obviously left", "through that hole in the fence", "I will finish up here then follow"});
	}
	
	public void execute(Player p, Entity collOwner)
	{
		p.setMessage(message);
		if(!blipped)
		{
			Art.blip.play();
			blipped = true;
		}
		
		Util.getDoor(p).unlock(1);
	}
}
