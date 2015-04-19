package biz.boulter.investigation.entity;

import java.awt.Graphics2D;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.state.GameState;

public class Fence extends Entity
{

	public Fence(GameState game, int x, int y)
	{
		super(game, x, y, 0, 0);
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(Art.fence, x(), y(), null);
	}
	
	public void keyEvent(int kc, boolean pressed)
	{
		
	}
	
	public String getType()
	{
		return "fence";
	}
	
	public int getZ()
	{
		return 90000;
	}

	@Override
	protected void update()
	{
		// TODO Auto-generated method stub
		
	}
	
}
