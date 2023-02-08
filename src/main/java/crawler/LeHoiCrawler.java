package crawler;

import model.lehoi.LeHoi;

import java.io.IOException;

public class LeHoiCrawler implements Crawl {
    @Override
    public void crawlData() throws IOException {
        LeHoi.getInfoLeHoiFromWikipedia("https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam#Danh_s%C3%A1ch_m%E1%BB%99t_s%E1%BB%91_l%E1%BB%85_h%E1%BB%99i");
    }
}
