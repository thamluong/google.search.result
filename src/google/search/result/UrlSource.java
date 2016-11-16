package google.search.result;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class UrlSource {

	final static String prefix = "https://www.google.com/search?q=";

	public static String getUrlSource(String query) throws IOException{
		// if query string is keyword, use googleService to get URL content 
		//if query string is an URL, query normally 

		URL url = null;
		String result = "";
		int nResult = 0 ;

//		if(query.contains("http://") || query.contains("https://"))
//			url = new URL(query);
//		else
			url = new URL(prefix + URLEncoder.encode(query, "UTF-8") + "&num=20");			
System.out.println("URL = "+url);
		URLConnection connection = url.openConnection();

		connection.setConnectTimeout(4000);
		connection.setReadTimeout(4000);
		connection.addRequestProperty("User-Agent", "Google Chrome/36");//put the browser name/version

		final Scanner reader = new Scanner(connection.getInputStream(), "UTF-8");  //scanning a buffer from object returned by http request

		int i = -1;
		while(reader.hasNextLine()){  
			i++;

			String line = reader.nextLine();
			result += "\n\n"+i+" . "+line;

			if(!line.contains("\"resultStats\">"))//line by line scanning for "resultstats" field because we want to extract number after it
				continue;

			try{
				nResult = Integer.parseInt(line.split("\"resultStats\">")[1].split("<")[0].replaceAll("[^\\d]", ""));//finally extract the number convert from string to integer
			}catch(Exception ex){

			}finally{
				System.out.println("Number of results = "+ nResult);
			}
		}

		reader.close();
		return result;
	}
}
