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
import model.nhanvat.NhanVat;

import java.util.ArrayList;

import static javafx.scene.text.TextAlignment.CENTER;

public class XCell_NhanVat extends XCell {
    public XCell_NhanVat() {
        super();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Check xem nhân vật có trong file json hay không
                ArrayList<NhanVat> listNhanVat = ReadDataFromJson.readNhanVatData();
                NhanVat nhanVatInfo = new NhanVat();
                for (NhanVat nhanVat : listNhanVat) {
                    if (nhanVat.getTen().equals(lastItem) || nhanVat.getTenKhac().equals(lastItem) && !lastItem.equals("Unknown")) {
                        nhanVatInfo = nhanVat;
                        break;
                    } else {
                        nhanVatInfo.setTen(lastItem);
                    }
                }

                Text tenNhanVat = new Text(nhanVatInfo.getTen());
                tenNhanVat.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
                tenNhanVat.setTextAlignment(CENTER);
                Text tenKhac = new Text(nhanVatInfo.getTenKhac());
                Text sinh = new Text(nhanVatInfo.getSinh());
                Text thanPhu = new Text(nhanVatInfo.getThanPhu());
                Text thanMau = new Text(nhanVatInfo.getThanMau());
                Text mat = new Text(nhanVatInfo.getMat());
                Text anTang = new Text(nhanVatInfo.getAnTang());
                Text chucVu = new Text(nhanVatInfo.getChucVu());
                Text phoiNgau = new Text(nhanVatInfo.getPhoiNgau());
                Text tonGiao = new Text(nhanVatInfo.getTonGiao());
                Text diaChi = new Text(nhanVatInfo.getDiaChi());
                Text nguyenNhanMat = new Text(nhanVatInfo.getNguyenNhanMat());
                Text queQuan = new Text(nhanVatInfo.getQueQuan());
                Text dangPhai = new Text(nhanVatInfo.getDangPhai());
                Text danToc = new Text(nhanVatInfo.getDanToc());
                Text hocVan = new Text(nhanVatInfo.getHocVan());
                ArrayList<String> vo = nhanVatInfo.getVo();
                ArrayList<String> hauDue = nhanVatInfo.getHauDue();
                ArrayList<String> links = nhanVatInfo.getLinks();

                //Add image


                StackPane nhanVatInfoWindow = new StackPane();
                GridPane tenImage = new GridPane();
                tenImage.add(new Text("Unknown"),0,0);
                try {
                    Image image = new Image(nhanVatInfo.getImage());
                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageView.setFitWidth(400);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    imageView.setCache(true);
                    imageView.setFitHeight(400);
                    imageView.setFitWidth(400);
                    tenImage.add(imageView, 0, 0);
                }
                catch (Exception e){
                }
                tenImage.add(tenNhanVat, 0, 1);

                //Create gridpand
                GridPane gridPane = new GridPane();
                //Setting the vertical and horizontal gaps between the columns
                gridPane.setVgap(5);
                gridPane.setHgap(5);
                //Setting the padding
                gridPane.setPadding(new Insets(10, 10, 10, 10));
                gridPane.add(new Text("Other names: "), 0, 0);
                gridPane.add(tenKhac, 1, 0);
                gridPane.add(new Text("Era: "), 0, 1);

                Button thoiKyButton = new Button(nhanVatInfo.getThoiKy());
                thoiKyButton.setOnAction(NewWindowThoiKyInfo.thoiKyInfoWindow(nhanVatInfo.getThoiKy()));
                gridPane.add(thoiKyButton, 1, 1);

                gridPane.add(new Text("Date of birth: "), 0, 2);
                gridPane.add(sinh, 1, 2);
                gridPane.add(new Text("Father: "), 0, 3);
                gridPane.add(thanPhu, 1, 3);
                gridPane.add(new Text("Mother: "), 0, 4);
                gridPane.add(thanMau, 1, 4);
                gridPane.add(new Text("Die: "), 0, 5);
                gridPane.add(mat, 1, 5);
                gridPane.add(new Text("Buried: "), 0, 6);
                gridPane.add(anTang, 1, 6);
                gridPane.add(new Text("Title: "), 0, 7);
                gridPane.add(chucVu, 1, 7);
                gridPane.add(new Text("Crossbred: "), 0, 8);
                gridPane.add(phoiNgau, 1, 8);
                gridPane.add(new Text("Religion: "), 0, 9);
                gridPane.add(tonGiao, 1, 9);
                gridPane.add(new Text("Address: "), 0, 10);
                gridPane.add(diaChi, 1, 10);
                gridPane.add(new Text("Death reason: "), 0, 11);
                gridPane.add(nguyenNhanMat, 1, 11);
                gridPane.add(new Text("Hometown: "), 0, 12);
                gridPane.add(queQuan, 1, 12);
                gridPane.add(new Text("Party: "), 0, 13);
                gridPane.add(dangPhai, 1, 13);
                gridPane.add(new Text("Ethnicity: "), 0, 14);
                gridPane.add(danToc, 1, 14);
                gridPane.add(new Text("Academic Lev: "), 0, 15);
                gridPane.add(hocVan, 1, 15);
                gridPane.add(new Text("Links: "), 0, 16);
                if(links.size()!=0) {
                    for (String link : links) {

                        Hyperlink hyperlink = new Hyperlink();
                        hyperlink.setText(link);
                        gridPane.add(hyperlink, 1, 16);
                    }
                }
                else {
                    gridPane.add(new Text("Unknown"),1,16);
                }
                gridPane.add(new Text("Spouses: "), 0, 17);
                if (vo != null) {
                    int i = 0;
                    int j = 0;
                    GridPane gridPaneVo = new GridPane();
                    for (String wife : vo) {
                        Button voButton = new Button(wife);
                        voButton.setOnAction(NewWindowNhanVatInfo.nhanVatInfoWindow(wife));
                        gridPaneVo.add(voButton,i,j);
                        if(i == 3){
                            j++;
                            i = 0;
                        }
                        else {
                            i++;
                        }
                    }
                    gridPane.add(gridPaneVo, 1, 17);
                } else {
                    gridPane.add(new Text("Unknown"), 1, 17);
                }
                gridPane.add(new Text("Descendants: "), 0, 18);
                if (hauDue != null) {
                    int i = 0;
                    int j =0;
                    GridPane gridPaneHauDue = new GridPane();
                    for (String haudue : hauDue) {
                        Button hauDueButton = new Button(haudue);
                        hauDueButton.setOnAction(NewWindowNhanVatInfo.nhanVatInfoWindow(haudue));
                        gridPaneHauDue.add(hauDueButton,i,j);
                        if(i == 3){
                            j++;
                            i = 0;
                        }
                        else {
                            i++;
                        }
                    }
                    gridPane.add(gridPaneHauDue, 1, 18);
                } else {
                    gridPane.add(new Text("Unknown"), 1, 18);
                }
                GridPane gridPaneScene = new GridPane();
                gridPaneScene.add(tenImage,0,0);
                gridPaneScene.add(gridPane,1,0);
                nhanVatInfoWindow.getChildren().add(gridPaneScene);
                Scene nhanVatInfoScene = new Scene(nhanVatInfoWindow);
                Stage newWindow = new Stage();
                newWindow.setTitle("Figure Info");
                newWindow.setScene(nhanVatInfoScene);
                newWindow.show();
            }
        });
    }
}
