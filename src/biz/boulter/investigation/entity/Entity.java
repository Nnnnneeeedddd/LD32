package biz.boulter.investigation.entity;

import biz.boulter.investigation.Collision;
import biz.boulter.investigation.GameObject;
import biz.boulter.investigation.Util;
import biz.boulter.investigation.state.GameState;

public abstract class Entity implements GameObject
{
	protected double x;
	protected double y;
	protected double xa;
	protected double ya;
	protected boolean allowedRight = true;
	protected boolean allowedLeft = true;
	protected boolean allowedUp = true;
	protected boolean allowedDown = true;
	public GameState game;
	public Collision top;
	public Collision bottom;
	protected Collision right;
	protected Collision left;
	protected int width;
	protected int height;
	protected boolean physEnabled = true;
	
	public Entity(GameState game, int x, int y, double xa, double ya)
	{
		this.game = game;
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
		top = new Collision(0, 0, 0, 0, -1);
		bottom = new Collision(0, 0, 0, 0, -1);
		right = new Collision(0, 0, 0, 0, -1);
		left = new Collision(0, 0, 0, 0, -1);
	}
	
	public void tick()
	{
		if(physEnabled)
		{
			right.setBounds((x() + width) - width / 10, ((y() + height / 2) + (height/5)/2)+2, width / 5, height / 3);
			left.setBounds(x() - width / 10, ((y() + height / 2) + (height/5)/2)+2, width / 5, height / 3);
			top.setBounds(x(), (y() + height / 2) + (height/5)/2, width, height / 5);
			bottom.setBounds(x(), (y() + height) - height/5, width, height / 5);
			
			if(doesCollide(top))
			{
				if(ya < 0) ya = 0;
				allowedUp = false;
			}else
			{
				allowedUp = true;
			}
			
			if(doesCollide(bottom))
			{
				if(ya > 0) ya = 0;
				allowedDown = false;
			}else
			{
				allowedDown = true;
			}
			
			if(doesCollide(right))
			{
				if(xa > 0) xa = 0;
				allowedRight = false;
			}else
				allowedRight = true;
			
			if(doesCollide(left))
			{
				if(xa < 0) xa = 0;
				allowedLeft = false;
			}else
				allowedLeft = true;
		}
		
		x += xa;
		y += ya;
		
		update();
	}
	
	public boolean doesCollide(Collision c)
	{
		if(Util.arrayContains(Collision.SOLID_COLL_IDS, game.doesCollide(c)))
		{
			return true;
		}
		
		return false;
	}
	
	public int x()
	{
		return (int) Math.round(x);
	}
	
	public int y()
	{
		return (int) Math.round(y);
	}
	
	protected abstract void update();
}
