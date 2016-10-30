
package google.search.result;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Result {
	public Result() {
		try {
			// TODO code application logic here

			final int Result;

			Scanner s1=new Scanner(System.in);
			String Str;
			System.out.println("Enter Query to search: ");//get the query to search
			Str=s1.next();
			getResultsCount(Str);

			//System.out.println("Results:"+ Result);
		} catch (IOException ex) {
			Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
		}      
	}

	private static void getResultsCount(final String query) throws IOException {
		final URL url;
		url = new URL("https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8"));
		final URLConnection connection = url.openConnection();

		connection.setConnectTimeout(60000);
		connection.setReadTimeout(60000);
		connection.addRequestProperty("User-Agent", "Google Chrome/36");//put the browser name/version

		final Scanner reader = new Scanner(connection.getInputStream(), "UTF-8");  //scanning a buffer from object returned by http request

		int i = -1, result = 0;
		while(reader.hasNextLine()){   //for each line in buffer
			i++;System.out.print(i+" . ");

			final String line = reader.nextLine();
			if(i < 10000) System.out.println(i +" = \n\t"+line);

			if(!line.contains("\"resultStats\">"))//line by line scanning for "resultstats" field because we want to extract number after it
				continue;

			try{        
				result = Integer.parseInt(line.split("\"resultStats\">")[1].split("<")[0].replaceAll("[^\\d]", ""));//finally extract the number convert from string to integer
			System.out.println("result = "+ result);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		reader.close();
	}
}