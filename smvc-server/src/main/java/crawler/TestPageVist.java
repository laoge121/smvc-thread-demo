package crawler;

import net.vidageek.crawler.Page;
import net.vidageek.crawler.PageVisitor;
import net.vidageek.crawler.Status;
import net.vidageek.crawler.Url;

/**
 * Created by pei.xu on 2015/10/19.
 */
public class TestPageVist implements PageVisitor {
    @Override
    public boolean followUrl(Url url) {
        return false;
    }

    @Override
    public void visit(Page page) {
         
    }

    @Override
    public void onError(Url url, Status status) {

    }
}
