
package google.search.result;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Result {


	public String getResultsGoogleSearch() {
		//System.out.println("Here is getResultsGoogleSearch");

		String query = "", result = "";
		try {
			Scanner s1=new Scanner(System.in);
			System.out.println("Keywords to search: ");//get the query to search

			query = s1.next();
			result = UrlSource.getUrlSource(query);
			//System.out.println("Result = "+result);

		} catch (IOException ex) {
			Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
		}    
		return result;
	}

	public String getResultsGoogleSearch(String query) {
		System.out.println("Here is getResultsGoogleSearch");

		String result = "";

		try {
			result = UrlSource.getUrlSource(query);
			if(result.equals("") || result == null ) return "";
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("No source");
		}   
		return result;
	}

	public String getResultsUrlSource(String query) {
		String result = "";
		try{

			result = UrlSource.getUrlSource(query);
			result = UrlSource.getUrlSource("http://"+query);
			result = UrlSource.getUrlSource("https://"+query);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return result;

	}

	public List<String> getLinks(String str){
		System.out.println("Here is getLinks");

		int beginIndex = 0, endIndex = 0, lastEndIndex = 0, n = str.length();
		List<String> links = new ArrayList<String>();

		while(endIndex < n){

			lastEndIndex = endIndex;
			beginIndex = str.indexOf("href=\"/url?q=", endIndex) + "href=\"/url?q=".length();
			endIndex = str.indexOf("&amp", beginIndex);

			if(endIndex <= lastEndIndex) break;
			String s = str.substring(beginIndex, endIndex);
			if(s.contains("webcache.googleusercontent.com")) continue;
			//System.out.println("Cite = "+s);
			links.add(s);
		}

		return links;
	}

	public List<String> getLinks2(String str){

		int beginIndex = 0, endIndex = 0, lastEndIndex = 0, n = str.length();
		List<String> links = new ArrayList<String>();

		while(endIndex < n){

			lastEndIndex = endIndex;
			beginIndex = str.indexOf("<cite>", endIndex) + "<cite>".length();
			endIndex = str.indexOf("</cite>", beginIndex);

			if(endIndex <= lastEndIndex) break;
			String s = str.substring(beginIndex, endIndex);
			//System.out.println("Cite = "+s);
			links.add(getStandardLink(s));
		}

		return links;
	}

	private String getStandardLink(String str){
		str = str.replace("<b>", "");
		str = str.replace("</b>", "");
		return str;

	}

	public List<String> getSubStringBetweenStrings(String str, String beginStr, String endStr, int beginIndex){
		//System.out.println("Here is getSubStringBetweenStrings");

		int endIndex = beginIndex, lastEndIndex = 0, n = str.length();
		List<String> links = new ArrayList<String>();

		while(endIndex < n){

			lastEndIndex = endIndex;
			beginIndex = str.indexOf(beginStr, endIndex) + beginStr.length();
			endIndex = str.indexOf(endStr, beginIndex);

			if(endIndex <= lastEndIndex) break;
			String s = str.substring(beginIndex, endIndex);
			//if(s.contains("webcache.googleusercontent.com")) continue;
			//System.out.println("Cite = "+s);
			links.add(s);
		}

		return links;		
	}

	public int countKeys(String str, String key){

		int i = 0, count = 0, length = str.length();
		while(str.indexOf(key, i) > 0){
			count++;
			i += length;
		}
		return count;
	}
}