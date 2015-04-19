package biz.boulter.investigation.collisionExecutors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import biz.boulter.investigation.Art;

public class MessageExecs
{

	private static ArrayList<CollInfo> idsandMessages = new ArrayList<CollInfo>();
	
	public static CollInfo getMessages(int id)
	{
		if(idsandMessages.isEmpty())
		{
			idsandMessages.add(new CollInfo(1, new String[]{"This is JUST a Table", "it genuinely has no", "significance whatsoever"}, null, "table"));
			idsandMessages.add(new CollInfo(2, new String[]{"Press 'E' to cut rope"}, new CutRope(), "dstand"));
			idsandMessages.add(new CollInfo(3, new String[]{"Press 'E' to investigate", "the knife."}, new InvestigateKnife(), null));
			idsandMessages.add(new CollInfo(4, new String[]{"Press 'E' to investigate", "the blood."}, new InvestigateBlood(), null));
			idsandMessages.add(new CollInfo(5, new String[]{"Press 'E' to leave.", "(Make SURE you remember all the clues)", "WARN: it IS possible to miss clues!"}, new Leave(), null));
			idsandMessages.add(new CollInfo(6, new String[]{"Press 'E' to investigate", "the blood trail"}, new BloodTrail(), null));
			idsandMessages.add(new CollInfo(7, new String[]{"Press 'E' to speak to kid"}, new KidExec(), "kid"));
			idsandMessages.add(new CollInfo(8, new String[]{"Press 'E' to pick up", "magnifying glass"}, new MagExec(), "mag"));
			idsandMessages.add(new CollInfo(9, new String[]{"Press 'E' to take", "a close look at the blood"}, new OilBlood(), "mag"));
			idsandMessages.add(new CollInfo(10, new String[]{"I am not touching those", "planks, I will", "get loads of splinters!"}, null, null));
			idsandMessages.add(new CollInfo(11, new String[]{"SECRET CLUE: This grass is really well cut!"}, null, null));
			idsandMessages.add(new CollInfo(12, new String[]{"I am so done", "looking at blood D:"}, null, null));
			idsandMessages.add(new CollInfo(13, new String[]{"A bit of red hair?", "it looks fake, like a wig?"}, null, null));
			idsandMessages.add(new CollInfo(14, new String[]{"The grass is", "really badly", "cut here!"}, null, null));
			idsandMessages.add(new CollInfo(15, new String[]{"This is a bit of a painted", "fingernail??"}, null, null));
			
			idsandMessages.add(new CollInfo(16, new String[]{"Press 'E' to accuse:", "The Banker"}, new Accusal("banker"), null));
			idsandMessages.add(new CollInfo(17, new String[]{"Press 'E' to accuse:", "The Chef"}, new Accusal("chef"), null));
			idsandMessages.add(new CollInfo(18, new String[]{"Press 'E' to accuse:", "The Gardener"}, new Accusal("gardener"), null));
			idsandMessages.add(new CollInfo(19, new String[]{"Press 'E' to accuse:", "The Teacher"}, new Accusal("teacher"), null));
		}
		
		for(int i = 0; i < idsandMessages.size(); i++)
		{
			if(idsandMessages.get(i).getId() == id)
			{
				return idsandMessages.get(i);
			}
		}
		
		return null;
	}
	
	public static class CollInfo
	{
		private int id;
		private BufferedImage hoverMessage = null;
		private CollisionExec executor = null;
		private String type;
		
		public CollInfo(int id, String[] hoverMessage, CollisionExec executor, String type)
		{
			this.type = type;
			this.id = id;
			
			if(hoverMessage == null)
				this.hoverMessage = null;
			else
				this.hoverMessage = Art.getTextImage(hoverMessage);
			this.executor = executor;
			
		}
		
		public CollisionExec getExecutor()
		{
			return executor;
		}
		
		public String getType()
		{
			return type;
		}
		
		public BufferedImage hoverMessage()
		{
			return hoverMessage;
		}
		
		public int getId()
		{
			return id;
		}
	}
}
