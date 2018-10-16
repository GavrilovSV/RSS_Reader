package edu.gavrilov.rss;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLFetcher;
import edu.gavrilov.xml.XMLReader;
import org.jsoup.Jsoup;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class NewsManager {

    public List listNews(List urls) {
        List allNews = new ArrayList();
        XMLReader xmlReader = new XMLReader();

        for (int i = 0; i < urls.size(); i++) {
            try{
                List news = xmlReader.listNews(urls.get(i).toString());
                news = news.subList(0, 20);
                if (news != null) {
                    allNews.addAll(news);
                }
            } catch (Exception e) {
                continue;
            }
        }
        allNews = sortNews(allNews);

        return allNews;
    }

/*    public String obtainPageContent(String url) {

        final de.l3s.boilerpipe.sax.HTMLDocument htmlDoc;
        try {
            htmlDoc = HTMLFetcher.fetch(new URL(url));
            final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
            String content = CommonExtractors.ARTICLE_EXTRACTOR.getText(doc);
            return content;
        } catch (IOException | SAXException | BoilerpipeProcessingException e) {
            e.printStackTrace();
        }

        return null;

    }*/


    private List sortNews(List listNews) {
        List news = new ArrayList();
        Iterator itEntries = listNews.iterator();
        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            List<SyndEnclosure> list = entry.getEnclosures();
            String pic = list.get(0).getUrl();

            String text = Jsoup.parse(entry.getDescription().getValue()).text();
/*            String content = obtainPageContent(entry.getLink());
            System.out.println(content);*/

            news.add(new News(entry.getTitle(),
                    entry.getLink(),
                    pic,
                    text,
                    entry.getPublishedDate().getTime()));
        }
        Collections.sort(news, snorderer);

        return news;
    }

    static Comparator<News> snorderer = new Comparator<News>() {
        public int compare(News o1, News o2) {
            return Long.valueOf(o2.getTime()).compareTo(Long.valueOf(o1.getTime()));
        }
    };


}
