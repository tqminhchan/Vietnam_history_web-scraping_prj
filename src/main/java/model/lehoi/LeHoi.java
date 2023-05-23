package model.lehoi;

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

public class LeHoi {
    protected String ngayBatDau;
    protected String viTri;
    protected String tenLeHoi;
    protected String lanDauToChuc;
    protected ArrayList<String> nhanVatLienQuan;
    protected String ghiChu;

    public LeHoi(){
        this.ngayBatDau = "Unknown";
        this.viTri = "Unknown";
        this.tenLeHoi = "Unknown";
        this.lanDauToChuc = "Unknown";
        this.nhanVatLienQuan = new ArrayList<>();
        this.ghiChu = "Unknown";
    }

    public void setTenLeHoi(String tenLeHoi) {
        this.tenLeHoi = tenLeHoi;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public String getViTri() {
        return viTri;
    }

    public String getTenLeHoi() {
        return tenLeHoi;
    }

    public String getLanDauToChuc() {
        return lanDauToChuc;
    }

    public ArrayList<String> getNhanVatLienQuan() {
        return nhanVatLienQuan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public static void getInfoLeHoiFromWikipedia(String url) throws IOException {
        // create Gson instance
        Gson gson = new Gson();

        // create a writer
        Writer writer = Files.newBufferedWriter(Paths.get("src/main/java/jsondata/LeHoi.json"));
        final Document doc = Jsoup.connect(url)
                .ignoreContentType(true)
                .timeout(0)
                .get();
        ArrayList<LeHoi> danhSachLehoi = new ArrayList<LeHoi>();

        Elements lehoiTable = doc.select("table.prettytable.wikitable");
        Elements lehoiRecords = lehoiTable.select("tr");
        int i=1;
        for(Element lehoiRecord:lehoiRecords) {
            if(i == 1) i++;
            else {
                LeHoi lehoiData = new LeHoi();
                Elements lehoi = lehoiRecord.select("td");
                if (lehoi.size() == 6) {
                    if (!lehoi.get(0).text().equals("")) {
                        lehoiData.ngayBatDau = lehoi.get(0).text();
                    }
                    if (!lehoi.get(1).text().equals("")) {
                        lehoiData.viTri = lehoi.get(1).text();
                    }
                    if (!lehoi.get(2).text().equals("")) {
                        lehoiData.tenLeHoi = lehoi.get(2).text();
                    }
                    if (!lehoi.get(3).text().equals("")) {
                        lehoiData.lanDauToChuc = lehoi.get(3).text();
                    }
                    if (!lehoi.get(4).text().equals("")) {
                        String[] relatedChars = lehoi.get(4).text().split(", ");
                        for (String relatedChar : relatedChars) {
                            lehoiData.nhanVatLienQuan.add(relatedChar);
                        }
                    }
                    if (!lehoi.get(5).text().equals("")) {
                        lehoiData.ghiChu = lehoi.get(5).text();
                    }
                    danhSachLehoi.add(lehoiData);
                }

            }
        }
        gson.toJson(danhSachLehoi, writer);
        // close writer
        writer.close();
    }

}