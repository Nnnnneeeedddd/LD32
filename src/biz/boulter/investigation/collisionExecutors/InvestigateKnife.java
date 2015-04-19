package biz.boulter.investigation.collisionExecutors;

import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Util;
import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.Player;

public class InvestigateKnife implements CollisionExec
{
	private BufferedImage message;
	private boolean blipped = false;
	
	public InvestigateKnife()
	{
		message = Art.getTextImage(new String[]{"This knife is clean, there", "is no way this knife", "was the weapon used"});
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
