package objects;
import javax.sound.sampled.*;

public class Sound {
	@SuppressWarnings("unused")
	private float vol;
	private boolean loop;
	private String url;
	private Clip clip;
	private AudioInputStream inputStream;
	
	public Sound(String url, float vol, boolean loop){
		this.vol = vol;
		this.loop = loop;
		this.url = url;
		initialize();
	}
	private void initialize() {
		try {
			clip = AudioSystem.getClip(); 
			inputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(url));
			clip.open(inputStream);
		} catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
	}
	public void play() {
		if (loop)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.setFramePosition(0);	
		clip.start();
	}
	public void stop() {
		clip.loop(0);
		clip.stop();
	}	
}
