package crawler;

import model.thoiky.ThoiKy;

public class ThoiKyCrawler implements Crawl {

    @Override
    public void crawlData() {
        ThoiKy.getInfoFromWiki("https://vi.wikipedia.org/wiki/Vua_Việt_Nam");
    }
}
