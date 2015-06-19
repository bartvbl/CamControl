package cam;

import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class CamAudioStreamer {

	public static void main(String[] args) {
		InputStream stream;
		try {
			stream = CamConnector.openStream(CamURL.CAM_AUDIO_STREAM);
			AudioStream audioStream = new AudioStream(stream);
			AudioPlayer.player.start(audioStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
