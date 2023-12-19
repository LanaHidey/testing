package com.system.gui;

import com.system.testing.AnimalInfo;
import com.system.testing.ui.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.assertions.api.Assertions;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ApplicationExtension.class)
public class UITest  {

    /**
     * Запускаем наше приложение
     */
    @Start
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("sample.fxml").toURL());
            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Калькулятор");
            stage.setScene(new Scene(root, 525, 500));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось запустить приложение для тестов");
        }
    }


    @Test()
    @Order(1)
    @DisplayName("Проверяем все кнопки")
    public void checkAllButton(FxRobot robot) {
        String menuText = robot.lookup("#menu").tryQuery().get().toString();
        assertTrue(menuText.contains("Меню"));
        robot.clickOn("#menu");
        String goText = robot.lookup("Поехали").tryQuery().get().toString();
        String exitText = robot.lookup("Выход").tryQuery().get().toString();
        assertTrue(goText.contains("Поехали"));
        assertTrue(exitText.contains("Выход"));
    }

    @Test
    @Order(2)
    @DisplayName("Проверяем ошибку")
    public void checkError(FxRobot robot) {
        robot.clickOn("#water").write("0");
        robot.clickOn("#barrier");
        robot.clickOn("#menu").clickOn("#button1");
        Assertions.assertThat(robot.lookup("#error").queryLabeled()).hasText("Введены не все данные!");
    }

    @Test
    @Order(3)
    @DisplayName("Проверяем работу")
    public void checkGoodWork(FxRobot robot) {
        robot.clickOn("#earth").write("5");
        robot.clickOn("#water").write("0");
        robot.clickOn("#barrier");
        robot.clickOn("#menu").clickOn("#button1");
        ObservableList<Object> resultList = robot.lookup("#result").queryTableView().getItems();
        // Животные
        org.junit.jupiter.api.Assertions.assertEquals("черепаха", ((AnimalInfo) resultList.get(0)).getName());
        assertEquals("рыба", ((AnimalInfo) resultList.get(1)).getName());
        assertEquals("лошадь", ((AnimalInfo) resultList.get(2)).getName());
        assertEquals("собака", ((AnimalInfo) resultList.get(3)).getName());
        assertEquals("кошка", ((AnimalInfo) resultList.get(4)).getName());
        // Результаты
        assertEquals("не может завершить дистанцию", ((AnimalInfo) resultList.get(0)).getInfo());
        assertEquals("не может завершить дистанцию", ((AnimalInfo) resultList.get(1)).getInfo());
        assertEquals("6.0 минут", ((AnimalInfo) resultList.get(2)).getInfo());
        assertEquals("20.0 минут", ((AnimalInfo) resultList.get(3)).getInfo());
        assertEquals("27.0 минут", ((AnimalInfo) resultList.get(4)).getInfo());
    }

    @Test()
    @Order(4)
    @DisplayName("Проверяем выход")
    public void testExit(FxRobot robot) {
        robot.clickOn("#menu").clickOn("#button2");
        Assertions.assertThat(robot.lookup("#exitInfo").queryLabeled()).hasText("Вы точно уверенны что хотите выйти?");
        robot.clickOn("Cancel");
        robot.clickOn("#menu").clickOn("#button2");
        Assertions.assertThat(robot.lookup("#exitInfo").queryLabeled()).hasText("Вы точно уверенны что хотите выйти?");
        robot.clickOn("OK");
    }
}