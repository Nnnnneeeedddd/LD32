package biz.boulter.investigation.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Collision;
import biz.boulter.investigation.state.GameState;

public class Cars extends Entity
{

	private int timer = 0;
	
	public Cars(GameState game)
	{
		super(game, 0, 0, 0, 0);
		
		timer = new Random().nextInt(60);
	}
	
	public void render(Graphics2D g)
	{
		
	}
	
	public void keyEvent(int kc, boolean pressed)
	{
		
	}
	
	public String getType()
	{
		return "cars";
	}

	@Override
	public int getZ()
	{
		return -1;
	}

	@Override
	protected void update()
	{
		if(timer > 0) timer--; else
		{
			timer = new Random().nextInt(60) + 35;
			game.addObj(new Car(game, x(), y(), new Random().nextBoolean()));
		}
	}
	
	public static class Car extends Entity
	{
		private static final double carSpeed = 3;
		private boolean right;
		private Collision coll;
		
		public Car(GameState game, int x, int y, boolean right)
		{
			super(game, x, y, right ? carSpeed : -carSpeed, 0);
			this.right = right;
			physEnabled = false;
			this.x = right ? -200 : 400;
			width = Art.cars.getTile(0, 0).getWidth();
			height = Art.cars.getTile(0, 0).getHeight();
			coll = new Collision(0, 0, 0, -1, 0);
		}

		public void render(Graphics2D g)
		{
			
			BufferedImage img = Art.cars.getTile(right ? 0 : 1, 0);
			
			g.drawImage(img, x(), right ? y() + 160 : y() + 210, (int) (img.getWidth() * 1.5), (int) (img.getHeight() * 1.5), null);
		}

		public void keyEvent(int kc, boolean pressed)
		{
			
		}

		public String getType()
		{
			return "car";
		}

		public int getZ()
		{
			return (right ? y() + 160 : y() + 210) - 20;
		}

		protected void update()
		{
			coll.setBounds(x() + 10, right ? y() + 175 : y() + 225, width, height/2);
			
			if(x > 500)
				game.removeObj(this);
			if(x < -200)
				game.removeObj(this);
			
		}
		
		public Collision getColl()
		{
			return coll;
		}
		
	}
	
}
