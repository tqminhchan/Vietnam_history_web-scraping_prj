module com.example.ui {
    requires javafx.controls;
    requires javafx.fxml;

//    requires org.controlsfx.controls;
//    requires org.kordamp.bootstrapfx.core;
    requires org.jsoup;
    requires com.google.gson;
    requires javafx.base;

    opens UI to javafx.fxml;
    opens model.nhanvat to com.google.gson;
    opens model.lehoi to com.google.gson;
    opens model.thoiky to com.google.gson;
    opens model.diadiem to com.google.gson;
    opens model.sukien to com.google.gson;

    exports UI;
}