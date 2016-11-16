package google.search.result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void getLinksList(){
		
		Result rs = new Result();
		List<String> links = new ArrayList<String>();
		
		String result = rs.getResultsGoogleSearch();	
		System.out.println("result: \n"+result);
		links.addAll(rs.getLinks(result));
		System.out.println("\n\nGet List links is OK");
		
		int i = 1;
		for(String l : links){
			System.out.println(i++ +" . "+l);
			//System.out.println("\t"+rs.getResultsUrlSource(l));
		}
		//System.out.println("result = "+UrlSource.getUrlSource("hibernate.org/", false));
	}
	
	public static void getKeywordsList(){
		Keyword k = new Keyword();
		k.getUrlKeywordsBySeo("http://www.tutorialspoint.com/");
	}
	
	public static void main(String args[]) throws IOException{
		//Main.getKeywordsList();
		Main.getLinksList();
	}
}
