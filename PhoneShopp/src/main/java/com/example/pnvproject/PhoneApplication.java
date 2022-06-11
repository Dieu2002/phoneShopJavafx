package com.example.pnvproject;

import com.example.pnvproject.dataa.DBConnect;
import com.example.pnvproject.dataa.Phone;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

public class PhoneApplication extends Application {

    private Scene scene;
    private static final String EMPTY = "";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBConnect DB = new DBConnect();
        ArrayList<Phone> phonelist = DB.getPhone();


        grid.add(new Label("Name:"), 0, 0);
        var tfName = new TextField();
        grid.add(tfName, 0, 1);

        grid.add(new Label("Price:"), 1, 0);
        var tfPrice = new TextField();
        grid.add(tfPrice, 1, 1);

        grid.add(new Label("Quantity:"), 2, 0);
        var tfQuantity = new TextField();
        grid.add(tfQuantity, 2, 1);



        // add
        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            Float price = Float.valueOf(tfPrice.getText());
            Integer quantity = Integer.valueOf(tfQuantity.getText());

            if (!name.equals(EMPTY) && !price.equals(EMPTY) && !quantity.equals(EMPTY) ) {
                DB.insertPhone(new Phone(name,price,quantity));
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        grid.add(btnAdd, 4, 1);

        //show
        for(int i = 0; i < phonelist.size(); i++){

            grid.add(new Label (phonelist.get(i).getName()), 0, i+2);
            grid.add(new Label ("$"+Float.valueOf(phonelist.get(i).getPrice())), 1, i+2);
            grid.add(new Label (" "+Integer.valueOf(phonelist.get(i).getQuantity())),2, i+2);

            // Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int id1 = Integer.parseInt(btnUpdate.getId());
                TextField tfname = (TextField) grid.getChildren().get(1);
                tfname.setText("" + phonelist.get(id1).getName());

                TextField tfprice = (TextField) grid.getChildren().get(3);
                tfprice.setText("" + phonelist.get(id1).getPrice());

                TextField tfquantity = (TextField) grid.getChildren().get(5);
                tfquantity.setText("" + phonelist.get(id1).getQuantity());
                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(5, 15, 5, 15));
                newbtnAdd.setOnAction(newe -> {
                    Integer Newid = phonelist.get(id1).id;
                    String Newname = tfname.getText();
                    Float Newprice = Float.valueOf(tfprice.getText());
                    Integer NewQuantity = Integer.valueOf(tfquantity.getText());
                    if (!Newname.equals(EMPTY)  && !Newprice.equals(EMPTY) && !NewQuantity.equals(EMPTY)) {
                        DB.updatePhone(new Phone(Newid, Newname, Newprice, NewQuantity));
                        try {
                            start(stage);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        return;
                    }
                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank!");
                    alert.showAndWait();
                });
                grid.add(newbtnAdd, 3, 1);
            });
            grid.add(btnUpdate, 3, i+2);

            // Delete
            var btnDelete = new Button("Delete");
            btnDelete.setId(String.valueOf(phonelist.get(i).id));
            btnDelete.setOnAction(e -> {
                int id3 = Integer.parseInt(btnDelete.getId());
                DB.deletePhone( id3);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 4, i+2);
        }

        scene = new Scene(grid, 1500, 1000);
        stage.setTitle("Phone Shop");
        stage.setScene(scene);
        stage.show();
    }
}
