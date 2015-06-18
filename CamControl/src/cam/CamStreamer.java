package cam;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jcodec.codecs.h264.H264Decoder;
import org.jcodec.common.JCodecUtil;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;


public class CamStreamer {
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(640*480); // Your frame data is stored in this buffer
		H264Decoder decoder = new H264Decoder();
		Picture out = Picture.create(640, 480, ColorSpace.YUV420); // Allocate output frame of max size
		Picture real = decoder.decodeFrame(bb, out.getData());
		BufferedImage bi = JCodecUtil.toBufferedImage(real); // If you prefere AWT image
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(bi)));
		frame.pack();
		frame.setVisible(true);
	}
}
