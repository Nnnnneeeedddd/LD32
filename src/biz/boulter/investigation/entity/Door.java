package biz.boulter.investigation.entity;

import java.awt.Graphics2D;
import biz.boulter.investigation.state.GameState;

public class Door extends Entity
{
	private boolean[] locks;
	
	public Door(GameState game, boolean[] locks)
	{
		super(game, 0, 0, 0, 0);
		this.locks = locks;
	}
	
	public void render(Graphics2D g)
	{
	}

	@Override
	public void keyEvent(int kc, boolean pressed)
	{
	}

	@Override
	public String getType()
	{
		return "door";
	}
	
	public void unlock(int index)
	{
		locks[index] = false;
	}
	
	public boolean isLocked()
	{
		for(int i = 0; i < locks.length; i++)
		{
			if(locks[i] == true)
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int getZ()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void update()
	{
		// TODO Auto-generated method stub
		
	}
	
}
