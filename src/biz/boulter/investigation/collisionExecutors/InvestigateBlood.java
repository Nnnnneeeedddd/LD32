package biz.boulter.investigation.collisionExecutors;

import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Util;
import biz.boulter.investigation.entity.Door;
import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.Player;

public class InvestigateBlood implements CollisionExec
{
	private BufferedImage message;
	private boolean blipped = false;
	
	public InvestigateBlood()
	{
		message = Art.getTextImage(new String[]
		{ "There is too much", "blood to be made", "with a knife.",
				"Hmm, there is way to much blood for", "any CONVENTIONAL weapon" });
	}
	
	public void execute(Player p, Entity collOwner)
	{
		p.setMessage(message);
		if (!blipped)
		{
			Art.blip.play();
			blipped = true;
		}
		
		Door door = Util.getDoor(p);
		door.unlock(0);
	}
}
