package biz.boulter.investigation;

import java.awt.Graphics2D;

public interface GameObject
{
	void tick();
	
	void render(Graphics2D g);
	
	void keyEvent(int kc, boolean pressed);
	
	String getType();
	
	int getZ();
}
