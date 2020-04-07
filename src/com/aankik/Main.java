package com.aankik;

import com.aankik.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent parent = FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));
//        Scene scene = new Scene(parent);
//        stage.setScene(scene);
        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();

    }
}