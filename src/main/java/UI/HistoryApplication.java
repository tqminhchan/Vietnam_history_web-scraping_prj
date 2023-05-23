package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HistoryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HistoryApplication.class.getResource("mainMenu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ベトナムの歴史 Web スクレイピング OOP プロジェクト");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}