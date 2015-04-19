package biz.boulter.investigation.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.state.GameState;

public class Kid extends Entity
{
	private BufferedImage message = null;
	private int messageTimer = 1000;
	
	public Kid(GameState game, int x, int y)
	{
		super(game, x, y, 0, 0);
		physEnabled = false;
	}

	@Override
	public void render(Graphics2D g)
	{
		g.drawImage(Art.kid, x(), y(), null);
		
		if(message != null)
		{
			if(messageTimer > 0)messageTimer--;else
			{
				message = null;
			}
			
			g.drawImage(message, x()-50, y()-(Art.kid.getHeight() + 5), null);
		}
	}

	@Override
	public void keyEvent(int kc, boolean pressed)
	{
		
	}

	@Override
	public String getType()
	{
		return "kid";
	}

	@Override
	public int getZ()
	{
		return y() - 30;
	}

	@Override
	protected void update()
	{
		ya = Math.random()-0.5;
	}
	
	public void setMessage(BufferedImage message)
	{
		this.message = message;
	}
	
}
