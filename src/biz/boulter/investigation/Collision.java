package biz.boulter.investigation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Collision
{
	private int x;
	private int y;
	private int width;
	private int height;
	private int id;
	public static final int[] SOLID_COLL_IDS = {0};
	
	public Collision(int x, int y, int width, int height, int id)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setBounds(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersects(Collision B)
	{
		int AminX = x;
		int AmaxX = x + width;
		int BminX = B.x;
		int BmaxX = B.x + B.width;
		
		int AminY = y;
		int AmaxY = y + height;
		int BminY = B.y;
		int BmaxY = B.y + B.height;
		
		if(AminX < BmaxX && AmaxX > BminX)
		{
			if(AminY < BmaxY && AmaxY > BminY)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(new Color(new Random(8752948573498573485l).nextInt(Integer.MAX_VALUE)));
		g.fillRect(x, y, width, height);
	}
	
	public void draw(Graphics2D g, Color col)
	{
		g.setColor(col);
		g.fillRect(x, y, width, height);
	}

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void printBounds()
	{
		System.out.printf("%d|%d|%d|%d\n", x, y, width, height);
	}
	
}
