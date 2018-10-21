package edu.gavrilov.rss;

import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import edu.gavrilov.services.ChannelsManager;
import edu.gavrilov.xml.XMLReader;
import org.jsoup.Jsoup;
import java.util.*;

public class NewsManager {

    private ChannelsManager channelsManager;
    private XMLReader xmlReader = new XMLReader();

    public NewsManager(ChannelsManager channelsManager) {
        this.channelsManager = channelsManager;
    }

    public List listNews() {

        List allNews = new ArrayList();
        List<String> urls;
        urls = channelsManager.getChannelsUrlsList();

        for (int i = 0; i < urls.size(); i++) {
            try{
                List news = xmlReader.listNews(urls.get(i));
                allNews.addAll(news);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        allNews = sortNews(allNews);

        return allNews;
    }

    private List sortNews(List listNews) {
        List news = new ArrayList();
        Iterator itEntries = listNews.iterator();
        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            List<SyndEnclosure> list = entry.getEnclosures();
            String pic = null;
            try {
                pic = list.get(0).getUrl();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
             }

            String text = Jsoup.parse(entry.getDescription().getValue()).text();


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
