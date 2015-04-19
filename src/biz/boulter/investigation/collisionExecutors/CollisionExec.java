package biz.boulter.investigation.collisionExecutors;

import biz.boulter.investigation.entity.Entity;
import biz.boulter.investigation.entity.Player;

public interface CollisionExec
{
	void execute(Player p, Entity collOwner);
}
