/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONObject;
import java.util.regex.*;
import org.json.JSONException;

public class SignUpController implements Initializable {

    @FXML
    private Button signUpBtn;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField retypePassword;
    @FXML
    private Label validationError;
    //private Parent homePageUI;
    @FXML
    private Hyperlink logLink;
    private Parent loginPageUI;
    private LoginController loginControl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setActionHandler(Stage stage) {
        //FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));

        try {
            loginPageUI = loginLoader.load();
            loginControl = (LoginController) loginLoader.getController();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        signUpBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Boolean validation = true;
                String pass = password.getText();
                String rePass = retypePassword.getText();
                if (validateUsername(username.getText())) {
                    if (validateEmail(email.getText())) {
                        if (validatePassword(password.getText())) {
                            if (!pass.equals(rePass)) {
                                System.out.println(password.getText());
                                System.out.println(retypePassword.getText());
                                System.out.println("YO");
                                validation = false;
                                validationError.setText("Your passwords doesn't match");
                            }
                        } else {
                            validation = false;
                            validationError.setText("Your password must be between 6 and 20 characters");
                        }
                    } else {
                        validation = false;
                        validationError.setText("Your email must be valid ex. example@example.topleveldomain");
                    }
                } else {
                    validation = false;
                    validationError.setText("Username must be between 6 and 20 characters");
                }
                if (validation) {
                    try {
                        System.out.println("YO YO");
                        sendPlayerData();
                    } catch (JSONException e) {
                        System.out.println(e);
                    }
                    if (Main.client.getErrorMessage().equals("")) {
                        System.out.println(Main.client.getErrorMessage());
                        System.out.println("MWT NFSk");
                        validationError.setText("");
                        stage.setScene(new Scene(loginPageUI));
                        loginControl.setActionHandler(stage);
                    } else if (!Main.client.getErrorMessage().equals("")){
                        System.out.println(Main.client.getErrorMessage());
                        validationError.setText(Main.client.getErrorMessage());
                    }
                }
            }
        });
        
        logLink.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(new Scene(loginPageUI));
                loginControl.setActionHandler(stage);
            }

        });
    }

    public void sendPlayerData() throws JSONException {
        Main.client.register(username.getText(), password.getText(), email.getText());
    }

    public boolean validateUsername(String username) {
        boolean validate = true;
        String regex = "^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        if (username.length() < 6 || username.length() > 20) {
            validate = false;
        }
        return validate && matcher.matches();
    }

    public boolean validatePassword(String password) {
        boolean validate = true;
        if (password.length() < 6 || password.length() > 20) {
            validate = false;
        }
        return validate;
    }

    public boolean validateEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
