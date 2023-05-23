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
import model.sukien.SuKien;

import java.util.ArrayList;

public class XCell_SuKien extends XCell {
    public XCell_SuKien(){
        super();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<SuKien> listSuKien = ReadDataFromJson.readSuKienData();
                SuKien suKienInfo = new SuKien();
                for (SuKien suKien : listSuKien) {
                    if (suKien.getTenSuKien().equals(lastItem)) {
                        suKienInfo = suKien;
                        break;
                    } else {
                        suKienInfo.setTenSuKien(lastItem);
                    }
                }
                Text tenSuKien = new Text(suKienInfo.getTenSuKien());
                Text thoiGian = new Text(suKienInfo.getThoiGian());
                Text diaDiem = new Text(suKienInfo.getDiaDiem());
                Text nguyenNhan = new Text(suKienInfo.getNguyenNhan());
                Text moTa = new Text(suKienInfo.getMoTa());
                Text ketQua = new Text(suKienInfo.getKetQua());
                ArrayList<String> nhanVatLienQuan = suKienInfo.getNhanVatLienQuan();
                //Create gridpand
                GridPane gridPane = new GridPane();
                //Setting the vertical and horizontal gaps between the columns
                gridPane.setVgap(5);
                gridPane.setHgap(5);
                //Setting the padding
                gridPane.setPadding(new Insets(10, 10, 10, 10));
                gridPane.add(new Text("Name of event: "),0,0);
                gridPane.add(tenSuKien,1,0);
                gridPane.add(new Text("Time happen: "),0,1);
                gridPane.add(thoiGian,1,1);
                gridPane.add(new Text("Place: "),0,2);
                gridPane.add(diaDiem,1,2);
                gridPane.add(new Text("Reason: "),0,3);
                gridPane.add(nguyenNhan,1,3);
                gridPane.add(new Text("Description: "),0,4);
                gridPane.add(moTa,1,4);
                gridPane.add(new Text("Result: "),0,5);
                gridPane.add(ketQua,1,5);
                gridPane.add(new Text("Related figures: "),0,6);
                if(nhanVatLienQuan.size() == 0){
                    gridPane.add(new Text("Unknown"),1,6);
                }else {
                    int i = 0;
                    int j = 0;
                    GridPane gridPaneNhanVatLienQuan = new GridPane();
                    for (String nhanVat : nhanVatLienQuan) {
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
                    gridPane.add(gridPaneNhanVatLienQuan, 1, 6);
                }
                StackPane suKienInfoWindow = new StackPane();
                suKienInfoWindow.getChildren().add(gridPane);
                Scene suKienInfoScene = new Scene(suKienInfoWindow);
                Stage newWindow = new Stage();
                newWindow.setTitle("Event Info");
                newWindow.setScene(suKienInfoScene);
                newWindow.show();
            }
        });
    }
}
