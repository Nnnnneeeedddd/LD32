package biz.boulter.investigation;

import java.awt.FontMetrics;

import biz.boulter.investigation.entity.Door;
import biz.boulter.investigation.entity.Player;

public class Util
{
	public static boolean arrayContains(int[] array, int item)
	{
		
		for(int i = 0; i < array.length; i++)
		{
			if(item == array[i])
			{
				return true;
			}
		}
		
		return false;
	}

	public static String getBiggestString(String[] lines, FontMetrics fmet)
	{
		String biggestString = "";
		for(int i = 0; i < lines.length; i++)
		{
			if(fmet.stringWidth(lines[i]) > fmet.stringWidth(biggestString))
			{
				biggestString = lines[i];
			}
		}
		
		return biggestString;
	}

	public static Door getDoor(Player p)
	{
		return (Door) p.game.getByType("door").get(0);
	}
}
