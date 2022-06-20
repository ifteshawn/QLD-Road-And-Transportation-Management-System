//AUTHOR NAME: Tedtya RADY
//Student ID: 12139186
package View;

import Model.DriverLicense;
import Presenter.DriverLicensePresenter;
import java.io.IOException;
import java.net.URL;
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
import java.security.SecureRandom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class DriverLicenseView implements Initializable {

    @FXML
    private TextField fNameTextField;

    @FXML
    private TextField mNameTextField;

    @FXML
    private TextField lNameTextField;

    @FXML
    private TextField contactNoTextField;

    @FXML
    private TextField dobTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField licenseTypeTextField;

    @FXML
    private TextField expiryDateTextField;

    @FXML
    private RadioButton mRadioButton;

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton fRadioButton;

    @FXML
    private Button logOutButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<DriverLicense> tableView;

    @FXML
    private TableColumn<DriverLicense, String> colFName;

    @FXML
    private TableColumn<DriverLicense, String> colMName;

    @FXML
    private TableColumn<DriverLicense, String> colLName;

    @FXML
    private TableColumn<DriverLicense, String> colGender;

    @FXML
    private TableColumn<DriverLicense, String> colContactNo;

    @FXML
    private TableColumn<DriverLicense, String> colDOB;

    @FXML
    private TableColumn<DriverLicense, String> colAddress;

    @FXML
    private TableColumn<DriverLicense, String> colLicenseType;

    @FXML
    private TableColumn<DriverLicense, String> colExpiryDate;

    @FXML
    private TableColumn<DriverLicense, String> colLicenseNum;

    @FXML
    private Button registerButton;

    DriverLicensePresenter presenter;
    private DriverLicense license;
    String gender = "";
    String licenseNumber = "";
    LogOut logout;
    private ObservableList<DriverLicense> dataTable;
    private ObservableList<DriverLicense> dataList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        groupRadioButton();
        license = new DriverLicense();
        logout = new LogOut();
        presenter = new DriverLicensePresenter();
        initTable();
        loadTableData();
    }

    public void groupRadioButton() {
        ToggleGroup group = new ToggleGroup();
        group.selectToggle(mRadioButton);
        group.selectToggle(fRadioButton);
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
        if (!blankField()) {
            if (mRadioButton.isSelected()) {
                gender = mRadioButton.getText();
            } else {
                gender = fRadioButton.getText();
            }
            license.setfName(fNameTextField.getText());
            license.setmName(mNameTextField.getText());
            license.setlName(lNameTextField.getText());
            license.setGender(gender);
            license.setContactNo(contactNoTextField.getText());
            license.setDob(dobTextField.getText());
            license.setAddress(addressTextField.getText());
            license.setLicenseType(licenseTypeTextField.getText());
            license.setExpiryDate(expiryDateTextField.getText());
            presenter.updateDriverLicense(license);
            System.out.println("vehicle successfully updated!!!");
            JOptionPane.showMessageDialog(null, "Vehicle successfully updated!!!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearAllTextField();
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(null, "Please input all fields before register", "Warining!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void deleteButton(ActionEvent event) {
        presenter.deleteDriverLicense(licenseNumber);
        JOptionPane.showMessageDialog(null, "Vehicle successfully registered!!!", "Success", JOptionPane.INFORMATION_MESSAGE);
        loadTableData();
    }

    @FXML
    void registerButton(ActionEvent event) throws IOException {
        groupRadioButton();
        if (!blankField()) {
            if (mRadioButton.isSelected()) {
                gender = mRadioButton.getText();
            } else {
                gender = fRadioButton.getText();
            }
            license.setfName(fNameTextField.getText());
            license.setmName(mNameTextField.getText());
            license.setlName(lNameTextField.getText());
            license.setGender(gender);
            license.setContactNo(contactNoTextField.getText());
            license.setDob(dobTextField.getText());
            license.setAddress(addressTextField.getText());
            license.setLicenseType(licenseTypeTextField.getText());
            license.setExpiryDate(expiryDateTextField.getText());
            generateLicenseNumber();//Generate License Number from System
            license.setLicenseNumber(licenseNumber);
            presenter.registerDriverLicense(license);
            JOptionPane.showMessageDialog(null, "License successfully registered!!!", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("License successfully registered!!!");
            clearAllTextField();
            loadTableData();
        } else {
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

    //Method to generate License Number
    public void generateLicenseNumber() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(10000000);
        licenseNumber = String.valueOf(num);
    }

    //Method to check blank field
    public boolean blankField() throws IOException {
        if (fNameTextField.getText().isEmpty()
                || mNameTextField.getText().isEmpty()
                || lNameTextField.getText().isEmpty()
                || contactNoTextField.getText().isEmpty()
                || dobTextField.getText().isEmpty()
                || addressTextField.getText().isEmpty()
                || licenseTypeTextField.getText().isEmpty()
                || expiryDateTextField.getText().isEmpty()) {
            System.out.println("Field Empty");
            return true;
        } else {
            return false;
        }
    }

    //method to clear all text Field
    public void clearAllTextField() {
        fNameTextField.setText("");
        mNameTextField.setText("");
        lNameTextField.setText("");
        contactNoTextField.setText("");
        dobTextField.setText("");
        addressTextField.setText("");
        licenseTypeTextField.setText("");
        expiryDateTextField.setText("");
    }

    public void initTable() {
        colFName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        colMName.setCellValueFactory(new PropertyValueFactory<>("mName"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colLicenseType.setCellValueFactory(new PropertyValueFactory<>("licenseType"));
        colExpiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        colLicenseNum.setCellValueFactory(new PropertyValueFactory<>("licenseNumer"));
    }

    public void loadTableData() {
        dataTable = FXCollections.observableArrayList();
        dataTable = presenter.selectDriverLicense();
        tableView.setItems(dataTable);
        dataList = presenter.selectDriverLicense();
        FilteredList<DriverLicense> filteredData = new FilteredList<>(dataList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(licenseSearchModel -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (licenseSearchModel.getfName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getmName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getlName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getGender().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getContactNo().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getDob().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getAddress().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getLicenseType().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getExpiryDate().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (licenseSearchModel.getLicenseNumber().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<DriverLicense> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    public void selectedLicense() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            DriverLicense selectedLicense = tableView.getSelectionModel().getSelectedItem();
            fNameTextField.setText(selectedLicense.getfName());
            mNameTextField.setText(selectedLicense.getmName());
            lNameTextField.setText(selectedLicense.getlName());
            contactNoTextField.setText(selectedLicense.getContactNo());
            dobTextField.setText(selectedLicense.getDob());
            addressTextField.setText(selectedLicense.getAddress());
            licenseTypeTextField.setText(selectedLicense.getLicenseType());
            expiryDateTextField.setText(selectedLicense.getExpiryDate());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a vehicle", "Warining!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

}
