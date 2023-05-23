package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.thoiky.ThoiKy;

import java.util.ArrayList;

public class XCell_ThoiKy extends XCell {
    public  XCell_ThoiKy(){
        super();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<ThoiKy> listThoiKy = ReadDataFromJson.readThoiKyData();
                ThoiKy thoiKyInfo = new ThoiKy();
                for (ThoiKy thoiKy : listThoiKy) {
                    if (thoiKy.getThoiKy().equals(lastItem) || thoiKy.getTenTrieuDai().equals(lastItem)) {
                        thoiKyInfo = thoiKy;
                        break;
                    } else {
                        thoiKy.setThoiKy(lastItem);
                    }
                }
                Text tenTrieuDai = new Text(thoiKyInfo.getTenTrieuDai());
                Text thoiKy = new Text(thoiKyInfo.getThoiKy());
                Text queHuong = new Text(thoiKyInfo.getQueHuong());
                Text kinhDo = new Text(thoiKyInfo.getKinhDo());
                Text triVi = new Text(thoiKyInfo.getTriVi());
                ArrayList<String> cacVua = thoiKyInfo.getCacVua();
                //Create gridpand
                GridPane gridPane = new GridPane();
                //Setting the vertical and horizontal gaps between the columns
                gridPane.setVgap(5);
                gridPane.setHgap(5);
                //Setting the padding
                gridPane.setPadding(new Insets(10, 10, 10, 10));
                gridPane.add(new Text("Reign: "),0,0);
                gridPane.add(tenTrieuDai,1,0);
                gridPane.add(new Text("Era: "),0,1);
                gridPane.add(thoiKy,1,1);
                gridPane.add(new Text("Hometown: "),0,2);
                gridPane.add(queHuong,1,2);
                gridPane.add(new Text("Capital: "),0,3);
                gridPane.add(kinhDo,1,3);
                gridPane.add(new Text("Time of reign: "),0,4);
                gridPane.add(triVi,1,4);
                gridPane.add(new Text("Kings: "),0,5);
                if(cacVua.size() == 0){
                    gridPane.add(new Text("Unknown"),1,5);
                }else {
                    int i = 0;
                    int j = 0;
                    GridPane gridPaneNhanVatLienQuan = new GridPane();
                    for (String nhanVat : cacVua) {
                        Button nhanVatButton = new Button(nhanVat);
                        nhanVatButton.setOnAction(NewWindowNhanVatInfo.nhanVatInfoWindow(nhanVat));
                        gridPaneNhanVatLienQuan.add(nhanVatButton,i,j);
                        if(i == 3){
                            j++;
                            i = 0;
                        }
                        else {
                            i++;
                        }
                    }
                    gridPane.add(gridPaneNhanVatLienQuan, 1, 5);
                }
                StackPane thoiKyInfoWindow = new StackPane();
                thoiKyInfoWindow.getChildren().add(gridPane);
                Scene thoiKyInfoScene = new Scene(thoiKyInfoWindow);
                Stage newWindow = new Stage();
                newWindow.setTitle("Era Info");
                newWindow.setScene(thoiKyInfoScene);
                newWindow.show();
            }
        });
    }
}
