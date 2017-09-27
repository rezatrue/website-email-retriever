import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	static Set<String> mailList;
	static Set<String> listofUrl;
	static String weburl = "http://yourwebsite.com";
	
	
	public static void main(String[] args) {
		mailList = new HashSet<>();
		listofUrl = new HashSet<String>();
		
		WebsiteHandler websiteHandler = new WebsiteHandler(weburl);

		listofUrl = websiteHandler.takingAnchor(weburl);
		
		Iterator<String> it =  listofUrl.iterator();
		
		while(it.hasNext()){
			String link = it.next();
			String sourceCode = websiteHandler.takeingSourceCode(link);
			mailList.addAll(websiteHandler.emailAddressFinder(sourceCode));
			
		}
		
		System.out.println("Total Page : "+ listofUrl.size());
		System.out.println("Number of Email : "+ mailList.size());
		
		for (String string : mailList) {
			System.out.println(string);
		}

	}
	

}
