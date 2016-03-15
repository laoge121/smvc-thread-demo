package crawler;

import net.vidageek.crawler.PageCrawler;
import net.vidageek.crawler.config.CrawlerConfiguration;

/**
 * Created by pei.xu on 2015/10/19.
 */
public class CrawlerTest {

    public static void main(String[] args) {
        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration("http://www.szpxe.com/");
        PageCrawler crawler = new PageCrawler(crawlerConfiguration);
        crawler.crawl(new TestPageVist());
    }
}
