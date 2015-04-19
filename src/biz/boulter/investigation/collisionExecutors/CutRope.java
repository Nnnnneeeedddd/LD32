package biz.boulter.investigation.collisionExecutors;

import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.*;

public class CutRope implements CollisionExec
{
	public void execute(Player p, Entity e)
	{
		((DeathStand) e).release();
		p.die();
	}
}
