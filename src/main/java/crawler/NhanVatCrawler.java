package crawler;

import model.nhanvat.NhanVat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NhanVatCrawler implements Crawl{
    protected static final ArrayList<String> detailedLinks = new ArrayList<>();

    public static void getLinksFromNguoiKeSu() {
        String crawlUrl = "https://nguoikesu.com/nhan-vat";
        System.out.println("Crawl from " + crawlUrl);

        try {
            final Document doc = Jsoup.connect(crawlUrl).ignoreContentType(true).timeout(0).get();

            // Lấy số trang có thể crawl
            Element pTag = doc.selectFirst(("p[class=com-content-category-blog__counter counter float-end pt-3 pe-2]"));

            assert pTag != null;
            String[] targetString = pTag.text().split(" ");
            int numberOfPages = Integer.parseInt(targetString[targetString.length - 1]);
            System.out.println("Số trang có thể crawl: " + numberOfPages);

            Elements pageHeaders = doc.select("div[class=page-header]");

            for (Element pageHeader : pageHeaders) {
                Element pageHeaderATag = pageHeader.selectFirst("a");
                if (pageHeaderATag != null) {
                    String link = "https://nguoikesu.com" + pageHeaderATag.attr("href");

                    // 1 số trường hợp là triều đại/địa danh thì không tính vào nhân vật
                    if (!link.contains("nha-")) {
                        if (!detailedLinks.contains(link)) {
                            System.out.println(pageHeader.text() + " - " + link);
                            detailedLinks.add(link);
                        }
                    }
                }
            }

            for (int i = 2; i <= numberOfPages; ++i) {
                System.out.println("\nCurrent page: " + i);
                String link = "https://nguoikesu.com/nhan-vat?start=" + String.valueOf((i - 1) * 5);

                try {
                    Document pagiDoc = Jsoup.connect(link).ignoreContentType(true).timeout(120000).get();

                    Elements pagiPageHeaders = pagiDoc.select("div[class=page-header]");

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
        NhanVat.getInfoFromNguoiKeSu(detailedLinks);
    }
}
