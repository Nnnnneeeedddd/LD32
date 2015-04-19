package biz.boulter.investigation.collisionExecutors;

import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Util;
import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.MagnifyingGlass;
import biz.boulter.investigation.entity.Player;

public class OilBlood implements CollisionExec
{
	private BufferedImage pDoesNotHaveGlassMessage;
	private BufferedImage oilMessage;
	private boolean blipped = false;
	
	public OilBlood()
	{
		pDoesNotHaveGlassMessage = Art.getTextImage(new String[]{"Hmm.. I need something to look", "at this with more detail"});
		oilMessage = Art.getTextImage(new String[]{"This blood... Is not", "just blood, it has some sort of", "slippery shiny substance", "in it. (like petrol or oil)"});
	}
	
	@Override
	public void execute(Player p, Entity collOwner)
	{
		MagnifyingGlass glass = (MagnifyingGlass) collOwner;
		if(!glass.doesPlayerHave())
		{
			p.setMessage(pDoesNotHaveGlassMessage);
		}else
		{
			if(!blipped)
			{
				Art.blip.play();
				blipped = true;
			}
			p.setMessage(oilMessage);
			Util.getDoor(p).unlock(2);
		}
	}
	
}
