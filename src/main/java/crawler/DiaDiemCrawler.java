package crawler;

import model.diadiem.DiaDiem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DiaDiemCrawler implements Crawl {
    protected static final ArrayList<String> detailedLinks = new ArrayList<>();

    public static void getLinksFromNguoiKeSu() {
        String crawlUrl = "https://nguoikesu.com/di-tich-lich-su";
        System.out.println("Crawl from " + crawlUrl);

        try {
            final Document doc = Jsoup.connect(crawlUrl).ignoreContentType(true).timeout(0).get();

            // Lấy số trang có thể crawl
            Element pTag = doc.selectFirst("div[class=com-tags-tag__pagination w-100]").firstElementChild();
            System.out.println(pTag);
            assert pTag != null;
            String[] targetString = pTag.text().split(" ");
            int numberOfPages = Integer.parseInt(targetString[targetString.length - 1]);
            System.out.println("Số trang có thể crawl: " + numberOfPages);

            Elements pageHeaders = doc.select("h3");

            for (Element pageHeader : pageHeaders) {
                Element pageHeaderATag = pageHeader.selectFirst("a");
                if (pageHeaderATag != null) {
                    String link = "https://nguoikesu.com" + pageHeaderATag.attr("href");
                    System.out.println(pageHeaderATag.text() + " - " + link);
                }
            }

            for (int i = 2; i <= numberOfPages; ++i) {
                System.out.println("\nCurrent page: " + i);
                String link = "https://nguoikesu.com/di-tich-lich-su?types[0]=1&start=" + String.valueOf((i - 1) * 10);

                try {
                    Document pagiDoc = Jsoup.connect(link).ignoreContentType(true).timeout(120000).get();

                    Elements pagiPageHeaders = pagiDoc.select("h3");

                    for (Element pagiPageHeader : pagiPageHeaders) {
                        Element pagiPHATag = pagiPageHeader.selectFirst("a");

                        if (pagiPHATag != null) {
                            String finalLink = "https://nguoikesu.com" + pagiPHATag.attr("href");
                            if (!finalLink.contains("nha-")) {
                                if (!detailedLinks.contains(finalLink)) {
                                    System.out.println(pagiPageHeader.text() + " - " + finalLink);
                                    detailedLinks.add(finalLink);
                                }
                            }
                        }
                    }
                } catch (IOException err) {
                    throw new RuntimeException(err);
                }
            }
//            System.out.println(detailedLinks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void crawlData() throws IOException {
        getLinksFromNguoiKeSu();
        DiaDiem.getInfoFromNguoiKeSu(detailedLinks);
    }
}