package model.nhanvat;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class NhanVat {
    protected String ten;
    protected String tenKhac;
    protected String thoiKy;
    protected String sinh;
    protected String thanPhu;
    protected String thanMau;
    protected String mat;
    protected String anTang;
    protected String chucVu;
    protected String phoiNgau;
    protected String tonGiao;
    protected String diaChi;
    protected String nguyenNhanMat;
    protected String queQuan;
    protected String dangPhai;
    protected String danToc;
    protected String hocVan;
    protected String image;
    protected ArrayList<String> vo;
    protected ArrayList<String> hauDue;
    protected ArrayList<String> links = new ArrayList<>();

    public NhanVat() {
        this.ten = "Unknown";
        this.tenKhac = "KUnknown";
        this.sinh = "Unknown";
        this.thanPhu = "Unknown";
        this.thanMau = "Unknown";
        this.mat = "Unknown";
        this.anTang = "Unknown";
        this.chucVu = "Unknown";
        this.nguyenNhanMat = "Unknown";
        this.queQuan = "Unknown";
        this.dangPhai = "Unknown";
        this.danToc = "Unknown";
        this.hocVan = "Unknown";
        this.image = "Unknown";
        this.diaChi = "Unknown";
        this.phoiNgau = "Unknown";
        this.tonGiao = "Unknown";
        this.thoiKy = "Unknown";
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public String getTenKhac() {
        return tenKhac;
    }

    public String getThoiKy() {
        return thoiKy;
    }

    public String getSinh() {
        return sinh;
    }

    public String getThanPhu() {
        return thanPhu;
    }

    public String getThanMau() {
        return thanMau;
    }

    public String getMat() {
        return mat;
    }

    public String getAnTang() {
        return anTang;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getPhoiNgau() {
        return phoiNgau;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getNguyenNhanMat() {
        return nguyenNhanMat;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getDangPhai() {
        return dangPhai;
    }

    public String getDanToc() {
        return danToc;
    }

    public String getHocVan() {
        return hocVan;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getVo() {
        return vo;
    }

    public ArrayList<String> getHauDue() {
        return hauDue;
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public static void getOneLink(String url) throws IOException {
        System.out.println(url);
        final Document doc = Jsoup.connect(url)
                .ignoreContentType(true)
                .timeout(0)
                .get();
        NhanVat nhanVat = new NhanVat();

        Element info = doc.selectFirst(".infobox");
        if (info != null) {
            for (Element row : info.select("tr")) {
                if (row.selectFirst("th:contains(Reign)") != null || row.selectFirst("th:contains(Era)") != null || row.selectFirst("th:contains(Royal fam)") != null || row.selectFirst("th:contains(Period)") != null) {
                    if (row.selectFirst("td") == null ) {
                            nhanVat.thoiKy = row.nextElementSibling().text();
                    } else {
                        nhanVat.thoiKy = row.selectFirst("td").text();
                    }
                }
            }
        }

        Element result = doc.selectFirst(".jch-lazyload");
        if (result != null) {
            nhanVat.image = "https://nguoikesu.com" + result.attr("data-src");
        }
        System.out.println(nhanVat.image);
        Gson gson = new Gson();
        String res = gson.toJson(nhanVat);

//        System.out.println(res);
    }

    public static void getInfoFromNguoiKeSu(ArrayList<String> urls) throws IOException {
        // create Gson instance
        Gson gson = new Gson();

        // create a writer
        Writer writer = Files.newBufferedWriter(Paths.get("src/main/java/jsondata/NhanVat.json"));
        ArrayList<NhanVat> danhsachnhanvat = new ArrayList<>();
        Element result;
        Elements results;
        for (String url : urls) {
            final Document doc = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .timeout(0)
                    .get();
            NhanVat nhanVat = new NhanVat();
            nhanVat.addLink(url);

            System.out.println("Crawling: "+ url);


            nhanVat.ten = Objects.requireNonNull(doc.selectFirst("div.page-header h2")).text();
            Element info = doc.selectFirst(".infobox");
            if (info != null) {
                for (Element row : info.select("tr")) {
                    if (row.selectFirst("th:contains(Reign)") != null || row.selectFirst("th:contains(Era)") != null || row.selectFirst("th:contains(Royal fam)") != null || row.selectFirst("th:contains(Period)") != null) {
                        if (row.selectFirst("td") == null) {
                            nhanVat.thoiKy = row.nextElementSibling().text();
                        } else {
                            nhanVat.thoiKy = row.selectFirst("td").text();
                        }
                    }
                    result = doc.selectFirst(".jch-lazyload");
                    if (result != null) {
                        nhanVat.image = "https://nguoikesu.com" + result.attr("data-src");
                    }
                    if (row.selectFirst("th:contains(Title)") != null || row.selectFirst("th:contains(Occupation)") != null || row.selectFirst("th:contains(Jobs)") != null) {
                        if (row.selectFirst("td") == null) {
                            nhanVat.chucVu = row.nextElementSibling().text();
                        } else {
                            nhanVat.chucVu = row.selectFirst("td").text();
                        }
                    }
                    if (row.selectFirst("th:contains(Full-name)") != null || row.selectFirst("th:contains(Other names)") != null || row.selectFirst("th:contains(Royal name)") != null || row.selectFirst("th:contains(Birth name)") != null || row.selectFirst("th:contains(Nickname)") != null || row.selectFirst("th:contains(Real name)") != null) {
                        if (row.selectFirst("td") == null) {
                            nhanVat.tenKhac = row.nextElementSibling().text();
                        } else {
                            nhanVat.tenKhac = row.selectFirst("td").text();
                        }
                    }
                    if (row.selectFirst("th:contains(Birth)") != null && row.selectFirst("td") != null) {
                        nhanVat.sinh = Objects.requireNonNull(row.selectFirst("td")).text();
                    }
                    if (row.selectFirst("th:contains(Death reason)") != null && row.selectFirst("td") != null) {
                        nhanVat.nguyenNhanMat = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Hometown)") != null && row.selectFirst("td") != null) {
                        nhanVat.queQuan = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Party)") != null && row.selectFirst("td") != null) {
                        nhanVat.dangPhai = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Accommodationa)") != null && row.selectFirst("td") != null) {
                        nhanVat.diaChi = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Ethnicity)") != null && row.selectFirst("td") != null) {
                        nhanVat.danToc = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Academic Lev)") != null && row.selectFirst("td") != null) {
                        nhanVat.hocVan = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Birth)") != null && row.selectFirst("td") != null) {
                        nhanVat.sinh = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Die)") != null && row.selectFirst("td") != null) {
                        nhanVat.mat = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Buried)") != null || row.selectFirst("th:contains(Buried Place)") != null) {
                        nhanVat.anTang = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Father)") != null || row.selectFirst("th:contains(Parents)") != null) {
                        nhanVat.thanPhu = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Mother)") != null && row.selectFirst("td") != null) {
                        nhanVat.thanMau = row.selectFirst("td").text();
                    }
                    if ((row.selectFirst("th:contains(Crossbred)") != null || row.selectFirst("th:contains(Husband)") != null) && row.selectFirst("td") != null) {
                        nhanVat.phoiNgau = row.selectFirst("td").text();
                    }
                    if (row.selectFirst("th:contains(Queens)") != null || row.selectFirst("th:contains(Concubines)") != null || row.selectFirst("th:contains(Wife)") != null || row.selectFirst("th:contains(Grand Queens)") != null || row.selectFirst("th:contains(Phi tần)") != null) {
                        results = row.select("li");
                        if (results.size() == 0 && row.selectFirst("td") != null) {
                            nhanVat.addVo(row.selectFirst("td").text());
                        } else {
                            for (Element vo : results) {
                                nhanVat.addVo(vo.text());
                            }
                        }
                    }
                    if (row.selectFirst("th:contains(Descendants)") != null || row.selectFirst("th:contains(Children)") != null) {
                        results = row.select("li");
                        if (results.size() == 0 && row.selectFirst("td") != null) {
                            nhanVat.addHauDue(row.selectFirst("td").text());
                        } else {
                            for (Element hauDue : results) {
                                nhanVat.addHauDue(hauDue.text());
                            }
                        }
                    }
                }
            }
            danhsachnhanvat.add(nhanVat);
        }
        gson.toJson(danhsachnhanvat, writer);
        writer.close();
    }

    protected void addLink(String link) {
        if (links.contains(link)) {
            return;
        }
        links.add(link);
    }

    protected void addVo(String item) {
        if (vo == null) {
            vo = new ArrayList<>();
        }
        if (vo.contains(item)) {
            return;
        }
        vo.add(item);
    }

    protected void addHauDue(String item) {
        if (hauDue == null) {
            hauDue = new ArrayList<>();
        }
        if (hauDue.contains(item)) {
            return;
        }
        hauDue.add(item);
    }
}
