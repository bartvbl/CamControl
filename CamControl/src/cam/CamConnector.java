package cam;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class CamConnector {
	private static final String host = "http://192.168.1.123:8080";
	
	public static InputStream openStream(CamURL targetURL) throws IOException {
		HttpURLConnection connection = openConnection(targetURL);
		return connection.getInputStream();
	}
	
	public static String executePost(CamURL targetURL, String urlParameters) {
			
		HttpURLConnection connection = null;
		try {
			//Create connection
			connection = openConnection(targetURL);

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", 
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", 
					Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");  

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			//Send request
			DataOutputStream wr = new DataOutputStream (
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.close();

			//Get Response  
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
			String line;
			while((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(connection != null) {
				connection.disconnect(); 
			}
		}
	}

	private static HttpURLConnection openConnection(CamURL targetURL) throws MalformedURLException, IOException {
		Authenticator.setDefault(new CamAuthenticator());
		String target = host + targetURL.address;
		URL url = new URL(target);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		return connection;
	}
}
