package biz.boulter.investigation.xml;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import biz.boulter.investigation.*;
import biz.boulter.investigation.entity.*;
import biz.boulter.investigation.state.GameState;

public class XMLGameReader
{
	private ArrayList<Collision> collisions = new ArrayList<Collision>();
	private ArrayList<GameObject> entities = new ArrayList<GameObject>();
	private BufferedImage img;
	
	public ArrayList<Collision> getColls()
	{
		return collisions;
	}
	
	public ArrayList<GameObject> getEntities()
	{
		return entities;
	}
	
	public BufferedImage getBackgroundImage()
	{
		return img;
	}
	
	public XMLGameReader(String xmlFile, GameState game)
	{
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(XMLGameReader.class.getResourceAsStream(xmlFile));
			
			NodeList colls = document.getElementsByTagName("collision");
			NodeList entities = document.getElementsByTagName("entity");
			NodeList levelBackground = document.getElementsByTagName("background");
			Element background = (Element) levelBackground.item(0);
			String levelBackgroundPath = background.getAttribute("path");
			img = Art.loadImage(levelBackgroundPath);
			
			for(int i = 0; i < colls.getLength(); i++)
			{
				Element collision = (Element) colls.item(i);
				int x = Integer.parseInt(collision.getAttribute("x"));
				int y = Integer.parseInt(collision.getAttribute("y"));
				int width = Integer.parseInt(collision.getAttribute("width"));
				int height = Integer.parseInt(collision.getAttribute("height"));
				int id = Integer.parseInt(collision.getAttribute("id"));
				
				collisions.add(new Collision(x, y, width, height, id));
			}
			
			for(int i = 0; i < entities.getLength(); i++)
			{
				Element entity = (Element) entities.item(i);
				String entityType = entity.getAttribute("type");
				int x = Integer.parseInt(entity.getAttribute("x"));
				int y = Integer.parseInt(entity.getAttribute("y"));
				
				if(entityType.equals("player"))
				{
					this.entities.add(new Player(game, x, y));
				}else if(entityType.equals("table"))
				{
					this.entities.add(new Table(game, x, y, 0, 0));
				}else if(entityType.equals("dstand"))
				{
					this.entities.add(new DeathStand(game, x, y, 0, 0));
				}else if(entityType.equals("door"))
				{
					int numOfLocks = Integer.parseInt(entity.getAttribute("locks"));
					boolean[] locks = new boolean[numOfLocks];
					for(int j = 0; j < locks.length; j++)
					{
						locks[j] = true;
					}
					
					this.entities.add(new Door(game, locks));
				}else if(entityType.equals("fence"))
				{
					this.entities.add(new Fence(game, x, y));
				}else if(entityType.equals("kid"))
				{
					this.entities.add(new Kid(game, x, y));
				}else if(entityType.equals("mag"))
				{
					this.entities.add(new MagnifyingGlass(game, x, y, 0, 0));
				}else if(entityType.equals("cars"))
				{
					this.entities.add(new Cars(game));
				}else
				{
					System.err.println("WARN: Entity type " + entityType + " does not exist");
				}
			}
		}catch(Exception e)
		{
			System.err.println("Program had error reading XML file");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
