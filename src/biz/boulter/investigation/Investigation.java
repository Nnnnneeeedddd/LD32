package biz.boulter.investigation;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import biz.boulter.investigation.state.State;
import biz.boulter.investigation.state.Menu;

public class Investigation extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static final String TITLE = "Investigation";
	public static final int SCALE = 2;
	public static final int WIDTH = 800 / SCALE;
	public static final int HEIGHT = 600 / SCALE;
	private static volatile boolean running = false;
	public static final float FONTSIZE = 10f;
	private FontMetrics fmet;
	private BufferedImage dbImage;
	private State currentState;
	
	private void tick()
	{
		currentState.tick();
	}
	
	private void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) dbImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		
		g.setFont(g.getFont().deriveFont(FONTSIZE));
		
		if(fmet == null)
			fmet = g.getFontMetrics();
		
		currentState.render(g);
		g.dispose();
		
		Graphics panelGraphics = bs.getDrawGraphics();
		panelGraphics.drawImage(dbImage, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		if(!Input.isFocused())
		{
			panelGraphics.setColor(new Color(0, 0, 0, 75));
			panelGraphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
			panelGraphics.setColor(Color.RED);
			panelGraphics.setFont(panelGraphics.getFont().deriveFont(50f));
			
			int fontWidth = panelGraphics.getFontMetrics().stringWidth("Click To Focus!") / 2;
			
			panelGraphics.drawString("Click To Focus!", ((WIDTH * SCALE) / 2) - fontWidth, (HEIGHT * SCALE) / 2);
		}
		
		panelGraphics.dispose();
		bs.show();
	}
	
	private void init()
	{
		Input input = new Input(this);
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(input);
		addFocusListener(input);
		
		Art.load(this);
		
		//SET DEFAULT STATE
		currentState = new Menu(this); // should be new Menu();
		
		dbImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Thread gameThread = new Thread(this, "GameLoop");
		gameThread.start();
	}
	
	public void keyEvent(int kc, boolean pressed)
	{
		currentState.keyEvent(kc, pressed);
	}
	
	public static void main(String[] args)
	{
		Investigation inv = new Investigation();
		inv.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		inv.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		inv.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		Frame f = new Frame(TITLE);
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				running = false;
			}
		});
		f.setResizable(false);
		f.add(inv);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		inv.init();
	}
	
	public void run()
	{
		running = true;
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000d / 60d;
		double delta = 0;
		boolean shouldRender = false;
		
		long secTime = System.currentTimeMillis();
		int ticks = 0;
		int frames = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			shouldRender = false;
			
			while(delta >= 1)
			{
				tick();
				ticks++;
				delta -= 1d;
				shouldRender = true;
			}
			
			if(shouldRender)
			{
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - secTime >= 1000)
			{
				System.out.printf("%d ticks, %d frames\n", ticks, frames);
				ticks = 0;
				frames = 0;
				secTime += 1000;
			}
		}
		
		System.exit(0);
	}

	public FontMetrics getFontMetrics()
	{
		return fmet;
	}

	public void setState(State s)
	{
		currentState = s;
	}
}
