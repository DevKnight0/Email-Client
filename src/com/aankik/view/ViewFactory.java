package com.aankik.view;

import com.aankik.EmailManager;
import com.aankik.controller.BaseController;
import com.aankik.controller.LoginWindowController;
import com.aankik.controller.MainWindowController;
import com.aankik.controller.OptionWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {

    private EmailManager emailManager;
    private ColorTheme colorTheme=ColorTheme.DEFAULT;
    private FontSize fontSize=FontSize.SMALL;
    private ArrayList<Stage> activeStage;
    private boolean mainViewInitialised=false;


    public  boolean ismainViewInitialised(){
        return mainViewInitialised;
    }
    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        this.activeStage=new ArrayList<>();
    }

    public void showLoginWindow() {
        System.out.println("show login window called");

        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initializeStage(controller);
    }
    public void showMainWindow(){
        System.out.println("main window called");

        BaseController controller = new MainWindowController(emailManager, this, "MainWindow.fxml");
        initializeStage(controller);
        mainViewInitialised=true;
    }

    public void showOptionsWindow(){
        System.out.println("options window called");
        BaseController controller = new OptionWindowController(emailManager, this, "OptionsWindow.fxml");
        initializeStage(controller);

    }


    private void initializeStage(BaseController baseController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        if(baseController.getFxmlName()=="LoginWindow.fxml")
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        activeStage.add(stage);

        if(baseController.getFxmlName()!="LoginWindow.fxml")
        updateStyles();
    }

    public  void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStage.remove(stageToClose);
    }

    public void updateStyles() {
        for(Stage stage: activeStage){
           Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
        }
    }


}