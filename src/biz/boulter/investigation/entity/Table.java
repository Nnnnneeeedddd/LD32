package biz.boulter.investigation.entity;

import java.awt.Graphics2D;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.state.GameState;

public class Table extends Entity
{
	
	public Table(GameState game, int x, int y, double xa, double ya)
	{
		super(game, x, y, xa, ya);
		physEnabled = false;
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(Art.table, x(), y(), null);
	}
	
	@Override
	public void keyEvent(int kc, boolean pressed)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getType()
	{
		return "table";
	}
	
	@Override
	public int getZ()
	{
		return y();
	}
	
	@Override
	protected void update()
	{
		// TODO Auto-generated method stub
		
	}
	
}
