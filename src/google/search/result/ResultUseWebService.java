package google.search.result;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ResultUseWebService {
	/*	now, if we use Web service, 
	 * the output result is such as the View page source function in browser, not the inspect function
	 * it's mean that it is the javascript files, not the HTML files
	 */

	@SuppressWarnings("deprecation")
	public ResultUseWebService(){

		URL u;
		InputStream is = null;
		DataInputStream dis;
		String s;

		try {
			u = new URL("https://www.google.com/search?q=hello+world");
			is = u.openStream();                                   // Open an input stream from the url
			dis = new DataInputStream(new BufferedInputStream(is)); //Convert the InputStream to a buffered DataInputStream
			                                                       //Buffering the stream makes the reading faster

			while ((s = dis.readLine()) != null) {
				System.out.println(s);
			}
		} catch (MalformedURLException mue) {

			System.out.println("Ouch - a MalformedURLException happened.");
			mue.printStackTrace();
			System.exit(1);

		} catch (IOException ioe) {

			System.out.println("Oops- an IOException happened.");
			ioe.printStackTrace();
			System.exit(1);

		} finally {
			try {
				is.close();
			} catch (IOException ioe) {
				// just going to ignore this one
			}}
	} 
} 