package biz.boulter.investigation.entity;

import java.awt.Graphics2D;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.state.GameState;

public class DeathStand extends Entity
{

	private boolean released = false;
	
	public DeathStand(GameState game, int x, int y, double xa, double ya)
	{
		super(game, x, y, xa, ya);
		physEnabled = false;
	}

	@Override
	public void render(Graphics2D g)
	{
		g.drawImage(Art.deathStand.getTile(released ? 0 : 1, 0), x(), y(), null);
		g.drawImage(Art.steelBarrel, x()-5, !released ? y()+20 : y() + 75, null);
		/*if(Art.steelBarrel == null)
			System.out.println("poop");*/
	}

	@Override
	public void keyEvent(int kc, boolean pressed)
	{
	}

	@Override
	public String getType()
	{
		return "dstand";
	}

	@Override
	public int getZ()
	{
		return y() + Art.deathStand.getTile(0, 0).getHeight() / 3;
	}

	@Override
	protected void update()
	{
	}

	public void release()
	{
		released = true;
	}
	
}
