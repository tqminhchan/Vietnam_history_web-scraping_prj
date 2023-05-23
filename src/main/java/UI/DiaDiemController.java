package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.diadiem.DiaDiem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DiaDiemController implements Initializable {

    ArrayList<String> words = getJsonInfo();
    private ArrayList<String > getJsonInfo() {
        ArrayList<DiaDiem> listDiaDiem = ReadDataFromJson.readDiaDiemData();
        ArrayList<String> word = new ArrayList<>();
        for (DiaDiem p : listDiaDiem){
            word.add(p.getTen());

        }
        return word;
    }

    private final SceneController SceneController = new SceneController();

    @FXML
    private TextField searchBar;
    @FXML
    private ListView<String> listView_diaDiem;

    public void switchToMainMenuInOther(ActionEvent event) throws IOException {
        SceneController.switchToMainMenu(event);
    }

    @FXML
    void search() {
        listView_diaDiem.getItems().clear();
        listView_diaDiem.getItems().addAll(searchList(searchBar.getText(), words));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView_diaDiem.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new XCell_DiaDiem();
            }
        });
        listView_diaDiem.getItems().addAll(words);
    }

    private List<String> searchList(String searchWords, ArrayList<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word ->
                input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }


}
