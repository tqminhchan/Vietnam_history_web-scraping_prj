package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.nhanvat.NhanVat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NhanVatController implements Initializable {
    ArrayList<String> words = getJsonInfo();

    private ArrayList<String > getJsonInfo() {
        ArrayList<NhanVat> listNhanVat = ReadDataFromJson.readNhanVatData();
        ArrayList<String> word = new ArrayList<>();
        for (NhanVat p : listNhanVat){
            word.add(p.getTen());
        }
        return word;
    }

    private final SceneController SceneController = new SceneController();

    @FXML
    private TextField searchBar;
    @FXML
    private ListView<String> listView_nhanVat;

    public void switchToMainMenuInOther(ActionEvent event) throws IOException {
        SceneController.switchToMainMenu(event);
    }

    @FXML
    void search() {
        listView_nhanVat.getItems().clear();
        listView_nhanVat.getItems().addAll(searchList(searchBar.getText(), words));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView_nhanVat.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new XCell_NhanVat();
            }
        });
        listView_nhanVat.getItems().addAll(words);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word ->
                input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }
}