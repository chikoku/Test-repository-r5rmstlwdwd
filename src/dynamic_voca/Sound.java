
package dynamic_voca;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Sound extends Thread {
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Sound(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File("sound/"+name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
