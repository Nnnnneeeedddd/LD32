package biz.boulter.investigation.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import biz.boulter.investigation.Art;
import biz.boulter.investigation.Collision;
import biz.boulter.investigation.GameObject;
import biz.boulter.investigation.Input;
import biz.boulter.investigation.Investigation;
import biz.boulter.investigation.entity.Cars;
import biz.boulter.investigation.entity.Player;
import biz.boulter.investigation.xml.XMLGameReader;

public class GameState implements State
{
	private static final int NUM_OF_LEVELS = 4;
	
	private ArrayList<GameObject> objs = new ArrayList<GameObject>();
	private ArrayList<GameObject> objsToAdd = new ArrayList<GameObject>();
	private ArrayList<GameObject> objsToRemove = new ArrayList<GameObject>();
	private ArrayList<Collision> startingColls = new ArrayList<Collision>();
	//private boolean ordering = false;
	private ArrayList<Integer> orderedInteger = new ArrayList<Integer>();
	private XMLGameReader[] levels;
	private int levelNum = -1;
	private boolean playerDead = false;
	private Investigation inv;
	private BufferedImage deathMessage = null;
	private BufferedImage deathSplat = null;
	private int playerX, playerY;
	private boolean loadingLevel = false;
	
	public GameState(Investigation inv)
	{
		this.inv = inv;
		levels = new XMLGameReader[NUM_OF_LEVELS];
		
		for(int i = 0; i < NUM_OF_LEVELS; i++)
		{
			levels[i] = new XMLGameReader("/levels/level"+i+"/level.xml", this);
		}
		
		Art.gameMusic.loop(1000);
		
		loadNextLevel();
	}
	
	public void loadNextLevel()
	{
		Input.releaseKeys();
		Art.gameMusic.loop(1000);
		playerDead = false;
		deathMessage = null;
		deathSplat = null;
		objs.clear();
		objsToRemove.clear();
		objsToAdd.clear();
		if(levelNum < NUM_OF_LEVELS-1)
		{
			levelNum++;
			levels[levelNum] = new XMLGameReader("/levels/level" + levelNum + "/level.xml", this);
			ArrayList<GameObject> entities = levels[levelNum].getEntities();
			for(int i = 0; i < entities.size(); i++)
			{
				System.out.println(entities.get(i));
				addObj(entities.get(i));
			}
			
			startingColls = levels[levelNum].getColls();
		}else
		{
			System.err.println("ERROR HAPPENED, SORRY :/");
		}
		loadingLevel = false;
	}
	
	public ArrayList<GameObject> getByType(String type)
	{
		ArrayList<GameObject> returnable = new ArrayList<GameObject>();
		for(int i = 0; i < objs.size(); i++)
		{
			if(objs.get(i).getType().equals(type))
			{
				returnable.add(objs.get(i));
			}
		}
		
		return returnable;
	}
	
	public int doesCollide(Collision c)
	{
		if(c.getX() < 0)
			return 0;
		
		if(c.getX() + c.getWidth() > 400)
			return 0;
		
		if(c.getY() < 0)
			return 0;
		
		if(c.getY() + c.getHeight() > 300)
			return 0;
		
		for(int i = 0; i < startingColls.size(); i++)
		{
			if(startingColls.get(i).intersects(c))
			{	
				return startingColls.get(i).getId();
			}
		}
		
		return -1;
	}
	
	public ArrayList<Collision> getSColls()
	{
		return startingColls;
	}
	
	public void setPlayerPos(int x, int y)
	{
		playerX = x;
		playerY = y;
	}
	
	public void tick()
	{
		objs.addAll(objsToAdd);
		objsToAdd.clear();
		objs.removeAll(objsToRemove);
		objsToRemove.clear();
		
		if(getByType("player").isEmpty())
		{
			if(!playerDead && !loadingLevel)
			{
				playerDead = true;
				deathMessage = Art.getTextImage(new String[]{"lol, you died :P", "Press 'R' to restart"});
				deathSplat = Art.splat;
				Art.gameMusic.stop();
				Art.gameMusic.setFramePosition(0);
				Art.death.play();
			}
		}
		
		for(int i = 0; i < objs.size(); i++)
		{
			objs.get(i).tick();
		}
	}
	
	public Investigation getMain()
	{
		return inv;
	}
	
	public void isCollidingWithCar(Player p)
	{
		ArrayList<GameObject> cars = getByType("car");
		if(!cars.isEmpty())
		{
			for(int i = 0; i < cars.size(); i++)
			{
				if(((Cars.Car) cars.get(i)).getColl().intersects(p.top))
				{
					p.die();
				}
			}
		}
	}
	
	public void render(Graphics2D g)
	{	
		g.drawImage(levels[levelNum].getBackgroundImage(), 0, 0, null);
		//ordering = true;
		orderList();
		
		if(deathSplat != null)
		{
			g.drawImage(deathSplat, playerX, playerY + 40, null);
			g.drawImage(deathMessage, playerX-30, playerY, null);
		}
		
		for(int i = 0; i < objs.size(); i++)
		{
			//System.out.println(objs.get(i));
			objs.get(i).render(g);
		}
	}
	
	public void orderList()
	{
		ArrayList<Integer> orderedInteger = new ArrayList<Integer>();
		for(int i = 0; i < objs.size(); i++)
		{
			orderedInteger.add(objs.get(i).getZ());
		}
		
		Collections.sort(orderedInteger);
		
		if(!orderedInteger.equals(this.orderedInteger))
		{
			ArrayList<GameObject> newObjs = new ArrayList<GameObject>();
			
			for(int i = 0; i < orderedInteger.size(); i++)
			{
				for(int j = 0; j < objs.size(); j++)//55
				{
					if(objs.get(j).getZ() == orderedInteger.get(i))
					{
						newObjs.add(objs.get(j));
						objs.remove(objs.get(j));
					}
				}
			}
			
			objs.clear();
			objs.addAll(newObjs);
		}
		
		//ordering = false;
	}
	
	public void addObj(GameObject obj)
	{
		objsToAdd.add(obj);
	}
	
	public void removeObj(GameObject obj)
	{
		objsToRemove.add(obj);
	}
	
	public void keyEvent(int kc, boolean pressed)
	{
		if(kc == KeyEvent.VK_R)
		{
			if(pressed)
			{	
				levelNum=-1;
				loadingLevel = true;
				loadNextLevel();
			}
		}
	}
}
