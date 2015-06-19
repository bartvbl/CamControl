package cam;

import java.util.Arrays;

public class H264Header {
	int headerID;
	short headerLength;
	short framerate;
	short frameWidth;
	short frameHeight;
	short frameBitrate;
	byte[] reserved;

	H264Header() {
		this.headerID = 0;
		this.headerLength = 0;
		this.framerate = 0;
		this.frameWidth = 0;
		this.frameHeight = 0;
		this.frameBitrate = 0;
		this.reserved = new byte[114];
	}
	
	public String toString() {
		return "H264 stream header - (\n"
				+ "\tHeader ID: " + headerID + "\n"
				+ "\tHeader Length: " + headerLength + "\n"
				+ "\tFramerate: " + framerate + "\n"
				+ "\tFrame width: " + frameWidth + "\n"
				+ "\tFrame height: " + frameHeight + "\n"
				+ "\tFrame bitrate: " + frameBitrate + "\n"
				+ "\tReserved section: " + Arrays.toString(reserved) + "\n)";
	}
}
