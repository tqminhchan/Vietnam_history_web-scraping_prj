package UI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.diadiem.DiaDiem;
import model.lehoi.LeHoi;
import model.nhanvat.NhanVat;
import model.sukien.SuKien;
import model.thoiky.ThoiKy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ReadDataFromJson {
    public static ArrayList<LeHoi> readLeHoiData(){
        Gson gson = new Gson();
        ArrayList<LeHoi> listLeHoi = new ArrayList<>();
        try {
            FileReader reader = new FileReader("src/main/java/jsondata/LeHoi.json");
            Type type = new TypeToken<ArrayList<LeHoi>>(){}.getType();
            listLeHoi = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listLeHoi;
    }

    public static ArrayList<NhanVat> readNhanVatData(){
        Gson gson = new Gson();
        ArrayList<NhanVat> listNhanVat = new ArrayList<>();
        try {
            FileReader reader = new FileReader("src/main/java/jsondata/NhanVat.json");
            Type type = new TypeToken<ArrayList<NhanVat>>(){}.getType();
            listNhanVat = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listNhanVat;
    }

    public static ArrayList<SuKien> readSuKienData(){
        Gson gson = new Gson();
        ArrayList<SuKien> listSuKien = new ArrayList<>();
        try {
            FileReader reader = new FileReader("src/main/java/jsondata/SuKien.json");
            Type type = new TypeToken<ArrayList<SuKien>>(){}.getType();
            listSuKien = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listSuKien;
    }
    public static ArrayList<ThoiKy> readThoiKyData(){
        Gson gson = new Gson();
        ArrayList<ThoiKy> listThoiKy = new ArrayList<>();
        try {
            FileReader reader = new FileReader("src/main/java/jsondata/ThoiKy.json");
            Type type = new TypeToken<ArrayList<ThoiKy>>(){}.getType();
            listThoiKy = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listThoiKy;
    }

    public static ArrayList<DiaDiem> readDiaDiemData(){
        Gson gson = new Gson();
        ArrayList<DiaDiem> listDiaDiem = new ArrayList<>();
        try {
            FileReader reader = new FileReader("src/main/java/jsondata/DiaDiem.json");
            Type type = new TypeToken<ArrayList<DiaDiem>>(){}.getType();
            listDiaDiem = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listDiaDiem;
    }

}
