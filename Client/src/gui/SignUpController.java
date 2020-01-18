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
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONObject;
import java.util.regex.*;
import org.json.JSONException;

/**
 *
 * @author Ismail_khadr
 */
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
    private Parent homePageUI;
    private HomePageController homePageControl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setActionHandler(Stage stage) {
        // TODO Auto-generated method stub
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        //FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));

        try {
            homePageUI = homePageLoader.load();
            homePageControl = (HomePageController) homePageLoader.getController();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        signUpBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                Boolean validation = true;
                if (validateUsername(username.getText())) {
                    if (validateEmail(email.getText())) {
                        if (!validatePassword(password.getText())) {
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
                        sendPlayerData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (Main.client.getPlayer().getId() == -1) {
                        validationError.setText(Main.client.getErrorMessage());
                        Main.client.getPlayer().setId(0);

                    } else if (Main.client.getPlayer().getId() > 0) {
                        stage.setScene(new Scene(homePageUI));
                        homePageControl.setActionHandler(stage);

                    } else {

                    }

                }
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
