package biz.boulter.investigation;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Input extends KeyAdapter implements FocusListener
{
	private static ArrayList<Integer> keys = new ArrayList<Integer>();
	private static boolean hasFocus = false;
	private Investigation game;
	
	public Input(Investigation game)
	{
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e)
	{
		game.keyEvent(e.getKeyCode(), true);
		
		if(!isKeyDown(e.getKeyCode()))
		{
			keys.add(new Integer(e.getKeyCode()));
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		game.keyEvent(e.getKeyCode(), false);
		
		if(isKeyDown(e.getKeyCode()))
		{
			keys.remove(new Integer(e.getKeyCode()));
		}
	}
	
	public static void releaseKeys()
	{
		keys.clear();
	}
	
	public static boolean isKeyDown(int kc)
	{
		return keys.contains(new Integer(kc));
	}
	
	public static boolean isFocused()
	{
		return hasFocus;
	}
	
	public void focusGained(FocusEvent e)
	{
		hasFocus = true;
	}
	
	public void focusLost(FocusEvent e)
	{
		keys.clear();
		hasFocus = false;
	}
}
