package cam;

public enum CamURL {
	CAM_CONTROL("/pantiltcontrol.cgi");
	
	private CamURL(String url) {
		this.address = url;
	}
	
	public final String address;
}
