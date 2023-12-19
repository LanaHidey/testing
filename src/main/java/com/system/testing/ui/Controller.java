package com.system.testing.ui;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.system.testing.AnimalInfo;
import com.system.testing.Calculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;


public class Controller {
    private Stage stage;
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
            alert.setTitle("Ошибка");
            alert.setHeaderText("");
            VBox content = new VBox();
            Label label = new Label("Введены не все данные!");
            label.setId("error");
            content.getChildren().addAll(label);
            alert.getDialogPane().setContent(content);
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтвердить выход");
        alert.setHeaderText("");
        VBox content = new VBox();
        Label label = new Label("Вы точно уверенны что хотите выйти?");
        label.setId("exitInfo");
        content.getChildren().addAll(label);
        alert.getDialogPane().setContent(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage.close();
        } else {
            System.out.println("Не выходим");
        }
    }





}
