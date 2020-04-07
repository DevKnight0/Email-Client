package com.aankik.controller;

import com.aankik.EmailManager;
import com.aankik.controller.services.LoginService;
import com.aankik.model.EmailAccount;
import com.aankik.view.ViewFactory;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginWindowController extends BaseController implements Initializable {
    @FXML
    private Button SignUp;
    @FXML
    private Label errorLabel;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField emailAddressField;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }



    @FXML
    void loginButtonAction()  {

        if(fieldsAreValid()){
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);
            loginService.start();
            loginService.setOnSucceeded(event -> {
                EmailLoginResult emailLoginResult= loginService.getValue();
                switch (emailLoginResult) {
                    case SUCCESS:
                        System.out.println("login succesfull!!!" + emailAccount);
                        if(!viewFactory.ismainViewInitialised()) {
                            viewFactory.showMainWindow();
                        }Stage stage = (Stage) errorLabel.getScene().getWindow();
                        viewFactory.closeStage(stage);
                        return;
                    case FAILED_BY_CREDENTIALS:
                        SignUp.setDisable(false);
                        errorLabel.setText("invalid credentials!");
                        return;
                    case FAILED_BY_UNEXPECTED_ERROR:
                        SignUp.setDisable(false);
                        errorLabel.setText("unexpected error!");
                        return;
                    default:
                        return;
                }

            });        }



    }
    private boolean fieldsAreValid() {
        if(emailAddressField.getText().isEmpty()) {
            errorLabel.setText("Please fill email");
            return false;
        }
        if(passwordField.getText().isEmpty()) {
            errorLabel.setText("Please fill password");
            return false;
        }
      SignUp.setDisable(true);
        return true;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}