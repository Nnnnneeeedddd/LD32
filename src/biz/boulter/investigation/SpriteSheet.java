package biz.boulter.investigation;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
	private BufferedImage[] tiles;
	private int widthIT;
	
	public SpriteSheet(BufferedImage mainImage, int widthIT, int heightIT)
	{
		this.widthIT = widthIT;
		
		int widthOfTile = mainImage.getWidth() / widthIT;
		int heightOfTile = mainImage.getHeight() / heightIT;
		
		tiles = new BufferedImage[widthIT * heightIT];
		
		for(int x = 0; x < widthIT; x++)
		{
			for(int y = 0; y < heightIT; y++)
			{
				tiles[y * widthIT + x] = mainImage.getSubimage(x * widthOfTile, y * heightOfTile, widthOfTile, heightOfTile);
			}
		}
	}
	
	public BufferedImage getTile(int tileX, int tileY)
	{
		return tiles[tileY * widthIT + tileX];
	}
}
