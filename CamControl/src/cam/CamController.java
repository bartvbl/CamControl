package cam;
import java.util.HashMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class CamController {
	
	private static final int camMoveSpeed = 2;
	private static final HashMap<String, Integer> controlCodes = new HashMap<String, Integer>();
	
	static {
		controlCodes.put("left", 3);
		controlCodes.put("topLeft", 0);
		controlCodes.put("top", 1);
		controlCodes.put("topRight", 2);
		controlCodes.put("right", 5);
		controlCodes.put("bottomRight", 8);
		controlCodes.put("bottom", 7);
		controlCodes.put("bottomLeft", 6);
		controlCodes.put("reset", 4);
	}
	
	public static void main(String[] args) {
		try {
			Controllers.create();
			Controller controller = Controllers.getController(6);
			System.out.println(controller.getName());
			while(!controller.isButtonPressed(0)) {
				controller.poll();
				sleep();
				float x = controller.getPovX();
				float y = -1 * controller.getPovY();
				if(x == 1) {
					executeControlCode("right");
				}
				if(x == -1) {
					executeControlCode("left");
				}
				if(y == 1) {
					executeControlCode("top");
				}
				if(y == -1) {
					executeControlCode("bottom");
				}
			}
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void executeControlCode(String controlString) {
		int controlCode = controlCodes.get(controlString);
		String parameters = "PanSingleMoveDegree="+camMoveSpeed+"&TiltSingleMoveDegree="+camMoveSpeed+"&PanTiltSingleMove=" + controlCode;
		CamConnector.executePost(CamURL.CAM_CONTROL, parameters);
	}

	
}
