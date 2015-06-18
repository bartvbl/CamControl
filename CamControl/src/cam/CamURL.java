package cam;

public enum CamURL {
	CAM_CONTROL("/pantiltcontrol.cgi"),
	CAM_VIDEO_STREAM("/dgh264.raw"),
	CAM_AUDIO_STREAM("/dgaudio.cgi");
	
	private CamURL(String url) {
		this.address = url;
	}
	
	public final String address;
}
