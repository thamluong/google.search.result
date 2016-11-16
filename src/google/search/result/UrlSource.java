package google.search.result;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//lay xong list keywords, nhug chua tinh so lan xhien
public class UrlSource {

	//num is the number of GG searched result, "tbm=" means search All, "tbm=vid" means search with only video
	//"q="+str is to fill keyword here
	final static String prefix = "https://www.google.com.vn/search?num=10&tbm=&q=";

	public static List<String> getSearchedKeywordsLinks(String query) throws Exception {

		return getWebpageLinks(prefix+URLEncoder.encode(query, "UTF-8"));
	}

	public static void getKeywords(String url){
		List<String> result = new ArrayList<String>();
		String[] keys ;
		Document doc;
		String text, reg = "//\\..*/$", temp;
		List<KeyRate> keysList = new ArrayList<KeyRate>();
		List<String> list = new ArrayList<String>();

		try {
			doc = Jsoup.connect(url).timeout(30000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
			text = doc.text();

			result = getWebpageLinks(url);
			for (String s: result){
				keys = s.split("(http://)|(https://)|(www.)|(.com)|(.org)|[/ . | - | _]");
				for(String s1 : keys){
					//System.out.print(s1+", ");
					if(!list.contains(s1)) list.add(s1);
				}
			}
			for(String s1 : list)
				System.out.print(s1+", ");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<String> getWebpageLinks(String url){

		List<String> links = new ArrayList<String>();
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(30000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();

			Elements link = doc.body().select("a[href]");
			for (Element l : link) {

				String temp = l.attr("href");
				if ((temp.startsWith("http") || temp.startsWith("com") || temp.startsWith("org")) 
						&& (!temp.contains("google") && !temp.contains("youtube"))){ 
					links.add(temp);
					//System.out.println("" +temp);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return links;

	}
	public static void main (String args[]) throws Exception{
		getKeywords("http://hibernate.org/orm/what-is-an-orm/");
		//getSearchedKeywordsLinks("orm");
	}
}
