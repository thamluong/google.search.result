package google.search.result;

import java.util.ArrayList;
import java.util.List;

public class Keyword {
	Result rs = new Result();
	public void getUrlKeywordsBySeo (String s){
		String result = rs.getResultsGoogleSearch(s);
		//System.out.println("result: \n"+result);
		List<String> keywordslist = rs.getSubStringBetweenStrings(result, "<title", "</title>", 0);
		List<String> linksList = rs.getLinks(result);
		
		System.out.println("Titles : "+keywordslist.size()+" result");
		for(String l: keywordslist)
			System.out.println(l);
		
		System.out.println("Links : "+linksList.size()+" result");
		for(String l: linksList)
			System.out.println(l);
		
		/*System.out.println("Sub titles : " + linksList.size()+" sub titles");
		List<String> temp = new ArrayList<String>();
		for(String l : linksList){
			System.out.println("\nURL: "+l);
			String s1 = rs.getResultsGoogleSearch(l);
			temp.addAll(rs.getSubStringBetweenStrings(s1, "<title", "</title>", 0));
		}
		
		
		for(String l: temp)
			System.out.println(l);*/
		
		System.out.println(" ---> Finished");
	}
	
	public static void main(String args[]){
		Keyword kw = new Keyword();
		String result = kw.rs.getResultsGoogleSearch();
		List<String> linksList = kw.rs.getLinks(result);
	}
}
