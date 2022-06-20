//AUTHOR NAME: Tedtya RADY
//Student ID: 12139186
package View;

import Model.DriverLicense;
import Model.Vehicle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Presenter.PersisterVehicle;
import Presenter.VehiclePresenter;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;


public class VehicleView implements Initializable {

    
     @FXML
    private TableView<Vehicle> tableView;

    @FXML
    private TableColumn<Vehicle, String> colPlate;

    @FXML
    private TableColumn<Vehicle, String> colMake;

    @FXML
    private TableColumn<Vehicle, String> colType;

    @FXML
    private TableColumn<Vehicle, String> colYear;

    @FXML
    private TableColumn<Vehicle, String> colColor;

    @FXML
    private TableColumn<Vehicle, String> colEngine;

    @FXML
    private TableColumn<Vehicle, String> colLicense;

    @FXML
    private Button logOutButton;
    
     @FXML
    private TextField searchTextField;

    @FXML
    private TextField plateNumTextField;

    @FXML
    private TextField makeTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private TextField colorTextField;

    @FXML
    private TextField engineNumTextField;

    @FXML
    private TextField licenseNumTextField;

    @FXML
    private RadioButton uteRadioButton;

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton suvRadioButton;

    @FXML
    private RadioButton sedanRadioButton;

    @FXML
    private Button registerButton;

    
    private String type;
    private Vehicle vehicle;
    LogOut logout;
    VehiclePresenter presenter;
    String licenseNumber = "";
    private ObservableList<Vehicle> dataList;
    private ObservableList<Vehicle> dataTable;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        groupRadioButton();
        vehicle = new Vehicle();
        logout = new LogOut();
        presenter = new VehiclePresenter();
        initTable();
        loadTableData();
    }
    
    public void groupRadioButton(){
        group = new ToggleGroup();
        group.selectToggle(suvRadioButton);
        group.selectToggle(uteRadioButton);
        group.selectToggle(sedanRadioButton);
    }
    
    @FXML
    void clearButton(ActionEvent event) {
        clearAllTextField();
    }
  
    @FXML
    void fetchData(ActionEvent event) {
        selectedLicense();
    }
    
    @FXML
    void backButton(ActionEvent event) throws IOException {
        System.out.println("Back to Login!");
        backScene();
    }
    
    
    @FXML
    void editButton(ActionEvent event) throws IOException {
        if (!blankField()){
            if(suvRadioButton.isSelected()){
                type = suvRadioButton.getText();
            }else if (uteRadioButton.isSelected()){
                type = uteRadioButton.getText();
            }else{
                type = sedanRadioButton.getText();
            }
            vehicle.setPlateNum(plateNumTextField.getText());
            vehicle.setMake(makeTextField.getText());
            vehicle.setType(type);
            vehicle.setYear(yearTextField.getText());
            vehicle.setColor(colorTextField.getText());
            vehicle.setEngineNum(engineNumTextField.getText());
            vehicle.setLicenseNumber(licenseNumTextField.getText());
            presenter.updateVehicle(vehicle);
            System.out.println("vehicle successfully updated!!!");
            JOptionPane.showMessageDialog(null, "Vehicle successfully updated!!!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearAllTextField();
            loadTableData();
        }else{
            JOptionPane.showMessageDialog(null, "Please input all fields before register", "Warining!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void deleteButton(ActionEvent event) {
        presenter.deleteVehicle(licenseNumber);
        JOptionPane.showMessageDialog(null, "Vehicle successfully registered!!!", "Success", JOptionPane.INFORMATION_MESSAGE);
        loadTableData();
    }

    @FXML
    void registerButton(ActionEvent event) throws IOException {
        if (!blankField()){
            if(suvRadioButton.isSelected()){
                type = suvRadioButton.getText();
            }else if (uteRadioButton.isSelected()){
                type = uteRadioButton.getText();
            }else{
                type = sedanRadioButton.getText();
            }
            vehicle.setPlateNum(plateNumTextField.getText());
            vehicle.setMake(makeTextField.getText());
            vehicle.setType(type);
            vehicle.setYear(yearTextField.getText());
            vehicle.setColor(colorTextField.getText());
            vehicle.setEngineNum(engineNumTextField.getText());
            vehicle.setLicenseNumber(licenseNumTextField.getText());
            presenter.registerVehicle(vehicle);
            System.out.println("vehicle successfully registered!!!");
            System.out.println(vehicle.toString());
            JOptionPane.showMessageDialog(null, "Vehicle successfully registered!!!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearAllTextField();
            loadTableData();
        }else{
            JOptionPane.showMessageDialog(null, "Please input all fields before register", "Warining!!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @FXML
    void logOutButton(ActionEvent event) throws IOException {
        logout.logout(logOutButton);
    }
    
    public void backScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    //Method to check blank field
    public boolean blankField() throws IOException{
        if( plateNumTextField.getText().isEmpty() ||
            makeTextField.getText().isEmpty()||
            yearTextField.getText().isEmpty()||
            colorTextField.getText().isEmpty()||
            engineNumTextField.getText().isEmpty()||
            licenseNumTextField.getText().isEmpty()){
            System.out.println("Empty");
            return true;
        }else{
            return false;
        }
    }
    
     public void clearAllTextField(){
        plateNumTextField.setText("");
        makeTextField.setText(""); 
        yearTextField.setText("");
        colorTextField.setText("");
        engineNumTextField.setText("");
        licenseNumTextField.setText("");
    }

    
    
    


    public void initTable() {
        colPlate.setCellValueFactory(new PropertyValueFactory<>("plateNum"));
        colMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colEngine.setCellValueFactory(new PropertyValueFactory<>("engineNum"));
        colLicense.setCellValueFactory(new PropertyValueFactory<>("licenseNumber"));
    }
    
    public void loadTableData() {
        dataTable = FXCollections.observableArrayList();
        dataTable = presenter.selectVehicle();
        tableView.setItems(dataTable);
        dataList = presenter.selectVehicle();
        FilteredList<Vehicle> filteredData = new FilteredList<>(dataList, b-> true);
        searchTextField.textProperty().addListener((observable,oldValue,newValue)->{
            filteredData.setPredicate(vehicleSearchModel ->{
            if(newValue.isEmpty() || newValue == null){
                return true;
            }
            String searchKeyword = newValue.toLowerCase();
            if(vehicleSearchModel.getPlateNum().toLowerCase().indexOf(searchKeyword)>-1){
                return true;
            }
            else if(vehicleSearchModel.getMake().toLowerCase().indexOf(searchKeyword)>-1){
                return true;
            }
            else if(vehicleSearchModel.getType().toLowerCase().indexOf(searchKeyword)>-1){
                return true;
            }
            else if(vehicleSearchModel.getYear().toLowerCase().indexOf(searchKeyword)>-1){
                return true;
            }
            else if(vehicleSearchModel.getColor().toLowerCase().indexOf(searchKeyword)>-1){
                return true;
            }
            else if(vehicleSearchModel.getEngineNum().toLowerCase().indexOf(searchKeyword)>-1){
                return true;
            }
            else if(vehicleSearchModel.getLicenseNumber().toLowerCase().indexOf(searchKeyword)>-1){
                return true;
            }else{
                return false;
            }
            });
        });
        
        SortedList<Vehicle> sortedData = new SortedList<>(filteredData);
        
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        
        tableView.setItems(sortedData);
    }
    
    public void selectedLicense(){
        if(tableView.getSelectionModel().getSelectedItem()!=null){
            Vehicle selectedVehicle = tableView.getSelectionModel().getSelectedItem();
            licenseNumber = selectedVehicle.getLicenseNumber();
            plateNumTextField.setText(selectedVehicle.getPlateNum());
            makeTextField.setText(selectedVehicle.getMake()); 
            yearTextField.setText(selectedVehicle.getYear());
            colorTextField.setText(selectedVehicle.getColor());
            engineNumTextField.setText(selectedVehicle.getEngineNum());
            licenseNumTextField.setText(selectedVehicle.getLicenseNumber());
        }else{
            JOptionPane.showMessageDialog(null, "Please select a driver license", "Warining!!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
