package biz.boulter.investigation.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Investigation;

public class Menu implements State
{
	private Investigation inv;
	
	public Menu(Investigation inv)
	{
		Art.menuMusic.loop(1000);
		this.inv = inv;
	}
	
	@Override
	public void render(Graphics2D g)
	{
		g.drawImage(Art.menu, 0, 0, null);
	}

	@Override
	public void tick()
	{
		
	}

	@Override
	public void keyEvent(int kc, boolean pressed)
	{
		if(!pressed)
		{
			if(kc == KeyEvent.VK_P)
			{
				Art.menuMusic.stop();
				inv.setState(new GameState(inv));
			}
		}
	}
	
}
