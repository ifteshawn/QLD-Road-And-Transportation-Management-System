/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AdminView implements Initializable {

    @FXML
    private Button licenseRegistration;

    @FXML
    private Button vehicleRegistration;
    
    @FXML
    private Button logOutButton;

    LogOut logout;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        logout = new LogOut();
    }    
    
    
    @FXML
    void logOutButton(ActionEvent event) throws IOException {
        logout.logout(logOutButton);
    }


    @FXML
    void licenseRegistration(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("DriverLicenseView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) licenseRegistration.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }


    @FXML
    void vehicleRegistration(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("VehicleView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) vehicleRegistration.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

   
    
}
