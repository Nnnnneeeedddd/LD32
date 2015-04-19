package biz.boulter.investigation.state;

import java.awt.Graphics2D;

public interface State
{
	void render(Graphics2D g);
	
	void tick();
	
	void keyEvent(int kc, boolean pressed);
}
