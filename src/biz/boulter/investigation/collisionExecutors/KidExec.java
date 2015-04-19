package biz.boulter.investigation.collisionExecutors;

import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Util;
import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.Kid;
import biz.boulter.investigation.entity.Player;

public class KidExec implements CollisionExec
{
	private BufferedImage playerQuestion;
	private BufferedImage kidResponse;
	private boolean blipped = false;
	
	public KidExec()
	{
		playerQuestion = Art.getTextImage(new String[]{"Did you see anything of ANY significance!", "You will be taken in for questioning later"});
		kidResponse = Art.getTextImage(new String[]{"I... d..didn't", "ss..s.ee anything", "but I heard a wierd RUMBLING sound like..", "a motor", "and der.. is blood eve..rywe.."});
	}
	
	public void execute(Player p, Entity collOwner)
	{
		p.setMessage(playerQuestion);
		((Kid)collOwner).setMessage(kidResponse);
		
		if(!blipped)
		{
			Art.blip.play();
			blipped = true;
		}
		
		Util.getDoor(p).unlock(0);
	}
	
}
