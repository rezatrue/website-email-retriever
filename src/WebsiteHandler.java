import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebsiteHandler {
	private String weburl;
	
	public WebsiteHandler(String weburl){
		this.weburl = weburl;
	}
	
	public HashSet<String> takingAnchor(String url){
		Set<String> list = new HashSet<>();
		
		Document doc = null; 
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Element content = doc.body();
		
		Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String linkText = link.text();
			String linkHref = link.absUrl("href");
			if(linkHref.contains(weburl))
				list.add(linkHref);
		}
		return (HashSet<String>) list;
	}
	
	public String takeingSourceCode(String url){
		Document doc = null; 
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element content = doc.body();
		
		return content.html();
	}
	
	public ArrayList<String> emailAddressFinder(String sourceCode){
		ArrayList<String> emailList = new ArrayList<String>();
		String RE_MAIL = "([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})";
		Pattern p = Pattern.compile(RE_MAIL);

		 Matcher m = p.matcher(sourceCode);
		    while(m.find()) {
		    	if(!emailList.contains(m.group(1))){
			        emailList.add(m.group(1));
		    	}
		    }

		return emailList;
	}

	
}
