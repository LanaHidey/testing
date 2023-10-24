package com.system.testing.ui;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.system.testing.AnimalInfo;
import com.system.testing.Calculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {
    @FXML
    public TextField earth;
    @FXML
    public TextField water;
    @FXML
    public CheckBox barrier;
    @FXML
    public TableView result;
    @FXML
    public TableColumn<AnimalInfo, String> animal;
    @FXML
    public TableColumn<AnimalInfo, String> info;

    private final Calculator calculator;

    public Controller() {
        calculator = new Calculator();
    }

    @FXML
    public void go() {
        if (earth.getText().isEmpty() || water.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибочка");
            alert.setHeaderText("");
             alert.setContentText("Нада ввести данные");
            alert.showAndWait();
            return;
        }
        Integer earthInt = Integer.parseInt(earth.getText());
        Integer waterInt = Integer.parseInt(water.getText());
        boolean barrierValue = barrier.isSelected();

        animal.setCellValueFactory(new PropertyValueFactory<>("name"));
        info.setCellValueFactory(new PropertyValueFactory<>("info"));
        ObservableList<AnimalInfo> usersData = FXCollections.observableArrayList();
        usersData.addAll(calculator.calc(earthInt, waterInt, barrierValue));
        result.setItems(usersData);

    }

    @FXML
    public void exit() {
        System.out.println("Выходим");
        System.exit(0);
    }





}
