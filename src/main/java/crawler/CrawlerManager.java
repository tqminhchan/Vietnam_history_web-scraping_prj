package crawler;

import java.io.IOException;

public class CrawlerManager {
    public CrawlerManager(){
    }

    public static void getData() throws IOException {
//         Crawl dia diem
        DiaDiemCrawler diadiemcrawler = new DiaDiemCrawler();
        diadiemcrawler.crawlData();
//
////         Crawl thoi ky
//        ThoiKyCrawler thoikycrawler = new ThoiKyCrawler();
//        thoikycrawler.crawlData();
//
////         Crawl su kien
//        SuKienCrawler sukiencrawler = new SuKienCrawler();
//        sukiencrawler.crawlData();
//
////         Crawl nhan vat
//        NhanVatCrawler nhanvatcrawler = new NhanVatCrawler();
//        nhanvatcrawler.crawlData();
//
////         Crawl le hoi
//        LeHoiCrawler lehoicrawler = new LeHoiCrawler();
//        lehoicrawler.crawlData();
    }

    public static void main(String[] args) throws IOException {
    }
}
