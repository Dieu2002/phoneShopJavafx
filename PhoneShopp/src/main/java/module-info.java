module com.example.pnvproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.pnvproject to javafx.fxml;
    exports com.example.pnvproject;
    exports com.example.pnvproject.dataa;
    opens com.example.pnvproject.dataa to javafx.fxml;
}