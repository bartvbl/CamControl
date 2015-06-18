package cam;
import java.net.Authenticator;
import java.net.PasswordAuthentication;


public class CamAuthenticator extends Authenticator {
	private static final String username = "admin"; 
	private static final String password = "Stroopwafel"; 
	
	public PasswordAuthentication getPasswordAuthentication() {
		return (new PasswordAuthentication(username, password.toCharArray()));
	}
}