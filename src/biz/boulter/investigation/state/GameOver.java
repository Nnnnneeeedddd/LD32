package biz.boulter.investigation.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameOver implements State
{
	private String accusal;
	
	private String bankerText = "There is very little evidence that points to the banker whatsoever\n\n"
			+ "I have no idea how you got to this conclusion :/ (lol)\n"
			+ "I very much suggest you replay the game, look at the clues. Try to work out who\n"
			+ "it actually is, and DON'T just guess";
	
	private String chefText = "There was a red hair, and the chef is bald\n"
			+ "AND there was a bit of painted fingernail (obviously transsexual)\n\n"
			+ "Seriously... No! just NO!";
	
	private String gardenerText = "Woh!, just Woh! I am genuinely impressed\n"
			+ "We have a genius in our mist everyone :O\n\n"
			+ "If you are wondering exactly what happened:\n"
			+ "The gardener murdered someone with one of those\n"
			+ "tractor lawn mower things. Hence the blood everywhere (and the trail), the\n"
			+ "rumbling the boy heard, and all the well cut grass in the gardners garden\n"
			+ "Oh! And I almost forgot, their was the engine oil in the blood as well!!!";
	
	private String teacherText = "I can see why you would think this, due to the\n"
			+ "two references to women \"red hair/fingernail\" however sadly\n"
			+ "you were wrong ;( try again!";
	
	public GameOver(String accusal)
	{
		this.accusal = accusal;
	}
	
	@Override
	public void render(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.WHITE);
		
		if(accusal.equals("banker"))
		{
			g.setFont(new Font("gobble", Font.PLAIN, 50));
			String title = "Incorrect!";
			int fontWidth = g.getFontMetrics().stringWidth(title);
			g.setColor(Color.RED);
			g.drawString(title, (400 / 2) - fontWidth / 2, 50);
			
			g.setColor(Color.WHITE);
			
			g.setFont(g.getFont().deriveFont(10f));
			string(bankerText, g, 100);
		}else if(accusal.equals("chef"))
		{
			g.setFont(new Font("gobble", Font.PLAIN, 50));
			String title = "Incorrect!";
			int fontWidth = g.getFontMetrics().stringWidth(title);
			g.setColor(Color.RED);
			g.drawString(title, (400 / 2) - fontWidth / 2, 50);
			
			g.setColor(Color.WHITE);
			
			g.setFont(g.getFont().deriveFont(10f));
			string(chefText, g, 100);
		}else if(accusal.equals("gardener"))
		{
			g.setFont(new Font("gobble", Font.PLAIN, 50));
			String title = "Correct!";
			int fontWidth = g.getFontMetrics().stringWidth(title);
			g.setColor(Color.GREEN);
			g.drawString(title, (400 / 2) - fontWidth / 2, 50);
			
			g.setColor(Color.WHITE);
			
			g.setFont(g.getFont().deriveFont(10f));
			string(gardenerText, g, 100);
		}else if(accusal.equalsIgnoreCase("teacher"))
		{
			g.setFont(new Font("gobble", Font.PLAIN, 50));
			String title = "Incorrect!";
			int fontWidth = g.getFontMetrics().stringWidth(title);
			g.setColor(Color.RED);
			g.drawString(title, (400 / 2) - fontWidth / 2, 50);
			
			g.setColor(Color.WHITE);
			
			g.setFont(g.getFont().deriveFont(10f));
			string(teacherText, g, 100);
		}
		
		g.drawString("'Restart Game' to return to main menu", 220, 290);
	}
	
	private void string(String text, Graphics2D g, int y)
	{
		String[] lines = text.split("\n");
		int fontHeight = g.getFontMetrics().getAscent();
		
		for(int i = 0; i < lines.length; i++)
		{
			g.drawString(lines[i], 10, y);
			y += fontHeight + 3;
		}
	}
	
	@Override
	public void tick()
	{
		
	}
	
	@Override
	public void keyEvent(int kc, boolean pressed)
	{
		
	}
	
}
