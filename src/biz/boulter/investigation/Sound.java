package biz.boulter.investigation;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineEvent.Type;

public class Sound
{
	private static ArrayList<Clip> clipsPlaying = new ArrayList<Clip>();
	
	private String sound;
	
	public Sound(String path)
	{
		this.sound = path;
	}
	
	public void play()
	{
		try
		{
			final Clip clip = AudioSystem.getClip();
			AudioInputStream sound = AudioSystem.getAudioInputStream(Sound.class.getResource(this.sound));
			clip.open(sound);
			clip.setFramePosition(0);
			clip.start();
			clipsPlaying.add(clip);
			
			clip.addLineListener(new LineListener()
			{
				public void update(LineEvent e)
				{
					if(e.getType() == Type.STOP)
					{
						clipsPlaying.remove(clip);
					}
				}
			});
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Clip getClip(String path)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			AudioInputStream sound = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
			clip.open(sound);
			clip.setFramePosition(0);
			
			return clip;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void cutAllSound()
	{
		for(int i = 0; i < clipsPlaying.size(); i++)
		{
			clipsPlaying.get(i).stop();
			clipsPlaying.remove(i);
		}
	}
}
