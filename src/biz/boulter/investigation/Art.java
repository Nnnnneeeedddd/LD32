package biz.boulter.investigation;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

public class Art
{
	public static SpriteSheet player;
	public static SpriteSheet deathStand;
	public static BufferedImage steelBarrel;
	public static BufferedImage table;
	public static BufferedImage splat;
	public static Clip menuMusic;
	public static Clip gameMusic;
	
	public static Sound death;
	public static Sound blip;
	private static FontMetrics fmet;
	private static Investigation game;
	public static BufferedImage fence;
	public static BufferedImage kid;
	public static BufferedImage mag;
	public static BufferedImage menu;
	public static SpriteSheet cars;
	
	public static void load(Investigation game)
	{
		Art.game = game;
		
		try
		{
			player = new SpriteSheet(loadImage("/character.png"), 3, 1);
			table = loadImage("/levels/level0/table.png");
			deathStand = new SpriteSheet(loadImage("/dstand.png"), 2, 1);
			steelBarrel = loadImage("/steelBarrel.png");
			death = new Sound("/death.wav");
			blip = new Sound("/blip.wav");
			splat = loadImage("/splat.png");
			menuMusic = Sound.getClip("/menu.wav");
			gameMusic = Sound.getClip("/game.wav");
			fence = loadImage("/levels/level1/fence.png");
			kid = loadImage("/levels/level1/child.png");
			mag = loadImage("/levels/level1/magglass.png");
			cars = new SpriteSheet(loadImage("/levels/level2/cars.png"), 2, 1);
			menu = loadImage("/menu.png");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static BufferedImage loadImage(String path)
	{
		try
		{
			return ImageIO.read(Art.class.getResourceAsStream(path));
		}catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
	
	public static BufferedImage getTextImage(String[] lines)
	{
		if(fmet == null)
			fmet = game.getFontMetrics();
		
		String longLine = Util.getBiggestString(lines, fmet);
		
		int width = fmet.stringWidth(longLine) + 6;
		int height = fmet.getHeight() * lines.length;
		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.getGraphics();
		g.setFont(g.getFont().deriveFont(Investigation.FONTSIZE));
		g.setColor(new Color(30, 30, 120, 100));
		g.fillRoundRect(0, 0, width, height, 10, 10);
		g.setColor(Color.WHITE);
		
		int y = fmet.getAscent();
		
		for(int i = 0; i < lines.length; i++)
		{
			g.drawString(lines[i], 3, y);
			y += fmet.getAscent();
		}
		
		g.dispose();
		
		return bi;
	}

	public static boolean ableToGenerateMessages()
	{
		return game.getFontMetrics() != null;
	}
}
