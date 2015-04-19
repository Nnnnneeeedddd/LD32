package biz.boulter.investigation.entity;

import java.awt.Graphics2D;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.state.GameState;

public class MagnifyingGlass extends Entity
{
	public boolean playerHas = false;;
	
	public MagnifyingGlass(GameState game, int x, int y, double xa, double ya)
	{
		super(game, x, y, xa, ya);
	}

	@Override
	public void render(Graphics2D g)
	{
		if(!playerHas)
			g.drawImage(Art.mag, x(), y(), null);
	}

	@Override
	public void keyEvent(int kc, boolean pressed)
	{
		
	}

	@Override
	public String getType()
	{
		return "mag";
	}

	@Override
	public int getZ()
	{
		return 0;
	}

	public void playerObtained()
	{
		playerHas = true;
	}
	
	public boolean doesPlayerHave()
	{
		return playerHas;
	}
	
	@Override
	protected void update()
	{	
	}
	
}
