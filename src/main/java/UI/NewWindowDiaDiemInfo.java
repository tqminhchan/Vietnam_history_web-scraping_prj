package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.diadiem.DiaDiem;

import java.util.ArrayList;

public class NewWindowDiaDiemInfo {
    public static EventHandler<ActionEvent> DiaDiemInfoWindow(String ten){
        EventHandler<ActionEvent> event =new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<DiaDiem> listDiaDiem = ReadDataFromJson.readDiaDiemData();
                DiaDiem diaDiemInfo = new DiaDiem();
                for (DiaDiem diaDiem : listDiaDiem) {
                    if (diaDiem.getTen().equals(ten)) {
                        diaDiemInfo = diaDiem;
                        break;
                    } else {
                        diaDiemInfo.setTen(ten);
                    }
                }
                Text ten = new Text(diaDiemInfo.getTen());
                ten.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
                Text viTri = new Text(diaDiemInfo.getViTri());
                Text quocGia = new Text(diaDiemInfo.getQuocGia());
                Text diaChi = new Text(diaDiemInfo.getDiaChi());
                Text thongTin = new Text(diaDiemInfo.getThongTin());
                Text khoiLap = new Text(diaDiemInfo.getKhoiLap());
                Text toaDo = new Text(diaDiemInfo.getToaDo());
                Button nguoiSangLapBtn = new Button(diaDiemInfo.getNguoiSangLap());
                nguoiSangLapBtn.setOnAction(NewWindowNhanVatInfo.nhanVatInfoWindow(diaDiemInfo.getNguoiSangLap()));
                Text leHoi = new Text(diaDiemInfo.getLeHoi());
                ArrayList<String> links = diaDiemInfo.getLinks();
                //Add image
                GridPane tenImage = new GridPane();
                tenImage.add(new Text("Unknown"),0,0);
                try {
                    Image image = new Image(diaDiemInfo.getImage());
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageView.setFitWidth(400);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    imageView.setCache(true);
                    tenImage.add(imageView, 0, 0);
                }
                catch (Exception e){
                }

                tenImage.add(ten, 0, 1);

                //Create gridpand
                GridPane gridPane = new GridPane();
                //Setting the vertical and horizontal gaps between the columns
                gridPane.setVgap(5);
                gridPane.setHgap(5);
                //Setting the padding
                gridPane.setPadding(new Insets(10, 10, 10, 10));

                gridPane.add(new Text("Location: "),0,0);
                gridPane.add(viTri,1,0);
                gridPane.add(new Text("Nation: "),0,1);
                gridPane.add(quocGia,1,1);
                gridPane.add(new Text("Address: "),0,2);
                gridPane.add(diaChi,1,2);
                gridPane.add(new Text("Information: "),0,3);
                gridPane.add(thongTin,1,3);
                gridPane.add(new Text("Established"),0,4);
                gridPane.add(khoiLap,1,4);
                gridPane.add(new Text("Coordinate: "),0,5);
                gridPane.add(toaDo,1,5);
                gridPane.add(new Text("Founder: "),0,6);
                gridPane.add(nguoiSangLapBtn,1,6);
                gridPane.add(new Text("Festival: "),0,7);
                gridPane.add(leHoi,1,7);
                gridPane.add(new Text("Links: "), 0, 8);
                if(links.size()!=0) {
                    for (String link : links) {

                        Hyperlink hyperlink = new Hyperlink();
                        hyperlink.setText(link);
                        gridPane.add(hyperlink, 1, 8);
                    }
                }
                else {
                    gridPane.add(new Text("Unknown"),1,8);
                }
                StackPane diaDiemInfoWindow = new StackPane();
                GridPane gridPaneScene = new GridPane();
                gridPaneScene.add(tenImage,0,0);
                gridPaneScene.add(gridPane,1,0);
                diaDiemInfoWindow.getChildren().add(gridPaneScene);
                Scene nhanVatInfoScene = new Scene(diaDiemInfoWindow);
                Stage newWindow = new Stage();
                newWindow.setTitle("Place Info");
                newWindow.setScene(nhanVatInfoScene);
                newWindow.show();
            }

        };
        return event;
    }
}
