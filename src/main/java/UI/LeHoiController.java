package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.lehoi.LeHoi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LeHoiController implements Initializable {
    ArrayList<String> words = getJsonInfo();
    private ArrayList<String > getJsonInfo() {
        ArrayList<LeHoi> listLeHoi = ReadDataFromJson.readLeHoiData();
        ArrayList<String> word = new ArrayList<>();
        for (LeHoi p : listLeHoi){
            word.add(p.getTenLeHoi());
        }
        return word;
    }

    private final SceneController SceneController = new SceneController();

    @FXML
    private TextField searchBar;
    @FXML
    private ListView<String> listView_leHoi;

    public void switchToMainMenuInOther(ActionEvent event) throws IOException {
        SceneController.switchToMainMenu(event);
    }

    @FXML
    void search() {
        listView_leHoi.getItems().clear();
        listView_leHoi.getItems().addAll(searchList(searchBar.getText(), words));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView_leHoi.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new XCell_LeHoi();
            }
        });
        listView_leHoi.getItems().addAll(words);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word ->
                input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }
}
