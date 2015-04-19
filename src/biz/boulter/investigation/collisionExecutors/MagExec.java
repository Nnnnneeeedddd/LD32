package biz.boulter.investigation.collisionExecutors;

import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.MagnifyingGlass;
import biz.boulter.investigation.entity.Player;

public class MagExec implements CollisionExec
{
	private BufferedImage message;
	private boolean blipped = false;
	
	public MagExec()
	{
		message = Art.getTextImage(new String[]{"I have picked up the magnifying glass"});
	}
	
	public void execute(Player p, Entity collOwner)
	{
		if(!blipped)
		{
			Art.blip.play();
			blipped = true;
		}
		
		((MagnifyingGlass) collOwner).playerObtained();
		p.setMessage(message);
		
	}
}
