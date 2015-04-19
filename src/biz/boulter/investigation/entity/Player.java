package biz.boulter.investigation.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Collision;
import biz.boulter.investigation.Input;
import biz.boulter.investigation.Util;
import biz.boulter.investigation.collisionExecutors.CollisionExec;
import biz.boulter.investigation.collisionExecutors.MessageExecs;
import biz.boulter.investigation.state.GameState;

public class Player extends Entity
{
	private static final int speed = 1;
	protected boolean movingRight = false;
	protected boolean movingLeft = false;
	protected boolean movingUp = false;
	protected boolean movingDown = true;
	private int bobTimer = 0;
	private boolean bobbingUp;
	private double rotation;
	private boolean rotRight;
	//private boolean e = true;
	private BufferedImage message = null;
	
	public Player(GameState game, int x, int y)
	{
		super(game, x, y, 0, 0);
		width = Art.player.getTile(0, 0).getWidth();
		height = Art.player.getTile(0, 0).getHeight();
	}
	
	protected void update()
	{
		game.isCollidingWithCar(this);
		
		int id;
		if(!Util.arrayContains(Collision.SOLID_COLL_IDS, id = game.doesCollide(right)))
		{
			if(id != -1)
			{
				if(Art.ableToGenerateMessages())
				{
					MessageExecs.CollInfo messgs = MessageExecs.getMessages(id);
					
					if(messgs != null)
					{
						if(messgs.hoverMessage() != null)
						{
							message = messgs.hoverMessage();
							
							CollisionExec ce;
							if((ce = messgs.getExecutor()) != null)
							{
								//e = true;
								
								if(Input.isKeyDown(KeyEvent.VK_E))
								{
									ce.execute(this, (messgs.getType()==null) ? null : (Entity)game.getByType(messgs.getType()).get(0));
								}
							}
						}
					}
				}
			}else
			{
				if(message != null)
					message = null;
				//e = false;
			}
		}else
		{
			if(message != null)
				message = null;
		}
		
		if(Input.isKeyDown(KeyEvent.VK_J))
		{
			System.out.println("X: " + x() + ", y: " + y());
		}
		
		if(Input.isKeyDown(KeyEvent.VK_DOWN))
		{
			if(allowedDown)
			{
				ya = speed;
				movingDown = true;
				movingUp = false;
			}
		}else if(Input.isKeyDown(KeyEvent.VK_UP))
		{
			if(allowedUp)
			{
				ya = -speed;
				movingUp = true;
				movingDown = false;
			}
		}else
			ya = 0;
		
		if(Input.isKeyDown(KeyEvent.VK_RIGHT))
		{
			if(allowedRight)
			{
				xa = speed;
				movingRight = true;
				movingLeft = false;
				movingUp = false;
				movingDown = false;
			}
		}else if(Input.isKeyDown(KeyEvent.VK_LEFT))
		{
			if(allowedLeft)
			{
				xa = -speed;
				movingLeft = true;
				movingRight = false;
				movingUp = false;
				movingDown = false;
			}
		}else
			xa = 0;
		
		if(xa != 0 || ya != 0)
		{
			if(rotRight)
			{
				if(rotation < 0.25)
					rotation+=0.1;
				else
					rotRight = false;
			}else
			{
				if(rotation > -0.25)
					rotation-=0.1;
				else
					rotRight = true;
			}
			
			if(bobTimer > 0) bobTimer--;else
			{
				bobbingUp = !bobbingUp;
				bobTimer = 5;
			}
			
			if(bobbingUp)
			{
				ya += 1.25;
			}else
			{
				ya -= 1.25;
			}
		}else
		{
			rotation = 0;
		}
		
	}
	
	public void render(Graphics2D g)
	{
		/*for(int i = 0; i < game.getSColls().size(); i++)
		{
			game.getSColls().get(i).draw(g);
		}*///TODO drawcolls
		
		AffineTransform old = g.getTransform();
		g.rotate(rotation, x() + 17, y() + 30);
		if(movingDown)
			g.drawImage(Art.player.getTile(0, 0), x(), y(), null);
		else if(movingUp)
			g.drawImage(Art.player.getTile(1, 0), x(), y(), null);
		
		if(!(movingDown || movingUp))
		{
			BufferedImage img = Art.player.getTile(2, 0);
			
			if(movingRight)
				g.drawImage(img, x(), y(), null);
			else if(movingLeft)
				g.drawImage(img, x() + img.getWidth(), y(), -img.getWidth(), img.getHeight(), null);
		}
		g.setTransform(old);
		
		if(message != null)
		{
			g.drawImage(message, x(), y() - (message.getHeight() + 3), null);
		}
		
		/*bottom.draw(g, Color.GREEN);
		top.draw(g, Color.RED);
		
		right.draw(g, Color.GREEN);
		left.draw(g, Color.RED);*/
	}
	
	public void setMessage(BufferedImage newMessage)
	{
		message = newMessage;
	}
	
	public void keyEvent(int kc, boolean pressed)
	{
	}
	
	public String getType()
	{
		return "player";
	}
	
	public int getZ()
	{
		return y();
	}

	public void die()
	{
		game.setPlayerPos(x(), y());
		game.removeObj(this);
	}
}
