//AUTHOR NAME: Tedtya RADY
//Student ID: 12139186
package View;

import Model.User;
import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Presenter.PersisterUserLogin;
import javax.swing.JOptionPane;


/**
 *
 * @author USER
 */
public class UserLoginView implements Initializable {

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    boolean reloadStatus;
    ResultSet rs;
    PersisterUserLogin persisterUser;
    private User user;

    @FXML
    void buttonClose(ActionEvent event) throws IOException, SQLException {
//        System.out.println("Exit Application!");
//        reloadUserLoginScene();
        persisterUser.deleteUser();
    }

    @FXML
    void buttonLogin(ActionEvent event) throws IOException, SQLException {
        if(!blankField()){
            userLogin();
            if (persisterUser.loggedIn(usernameTextField.getText(),passwordTextField.getText())){
                System.out.println("Login Success!!!");
                JOptionPane.showMessageDialog(null, "Login Success!!!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) buttonLogin.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }else{
                JOptionPane.showMessageDialog(null, "Username or Password incorrect!! Please try again", "Warining!!!", JOptionPane.ERROR_MESSAGE);
                System.out.println("Login Failed!!!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please input all fields before register", "Warining!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        persisterUser = new PersisterUserLogin();
        user = new User();
    }

    public void reloadUserLoginScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonLogin.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void userLogin() throws IOException, SQLException {
       user.setName("admin");
       user.setPassword("pwdpwd");
       persisterUser.addUsers(user);
       
    }
    
    public boolean blankField() throws IOException{
            if( usernameTextField.getText().isEmpty()||passwordTextField.getText().isEmpty()){
                System.out.println("Field Empty");
                return true;
            }else{
                return false;
            }
    }

}
