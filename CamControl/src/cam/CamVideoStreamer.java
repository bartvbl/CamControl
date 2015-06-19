package cam;

import java.io.IOException;
import java.io.InputStream;

public class CamVideoStreamer {
	public static void main(String[] args) throws Exception {
		InputStream stream = CamConnector.openStream(CamURL.CAM_VIDEO_STREAM);
		H264Header header = readHeader(stream);
		
		System.out.println(header);

	}

	private static H264Header readHeader(InputStream stream) throws IOException {
		byte[] headerBuffer = new byte[128];
		H264Header header = new H264Header();
		
		stream.read(headerBuffer, 0, 128);
		
		header.headerID = headerBuffer[0] + (headerBuffer[1] << 8) + (headerBuffer[2] << 16) + (headerBuffer[3] << 24);
		header.headerLength = 	(short)((headerBuffer[4] & 255)  + 	(headerBuffer[5] << 8));
		header.framerate = 		(short)((headerBuffer[6] & 255)  + 	(headerBuffer[7] << 8));
		header.frameWidth = 	(short)((headerBuffer[8] & 255)  + 	(headerBuffer[9] << 8));
		header.frameHeight = 	(short)((headerBuffer[10] & 255) + 	(headerBuffer[11] << 8));
		header.frameBitrate = 	(short)((headerBuffer[12] & 255) + 	(headerBuffer[13] << 8));
		
		for (int i = 14; i < 127; i++) {
			header.reserved[i - 14] = headerBuffer[i];
		}
		return header;
	}
}
