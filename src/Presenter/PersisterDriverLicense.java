//AUTHOR NAME: Tedtya RADY
//Student ID: 12139186
package Presenter;

import Model.DriverLicense;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersisterDriverLicense implements IPersistDriverLicense {

//    variable and databases delcaration
    private final String MYSQL_URL;
    private final String DB_URL;
    private final String USERNAME;
    private final String PASSWORD;
    private Connection sqlConnection;
    private Connection dbConnection;
    private PreparedStatement createDBQRTA,createTableUsers;
    private PreparedStatement registerLicense, selectLicense,
                   updateLicense, deleteLicense,deleteAllLicense;
    
    public PersisterDriverLicense() {

        //database variable values
        MYSQL_URL = "jdbc:mysql://localhost:3306";
        DB_URL = MYSQL_URL + "/QRTA";
        USERNAME = "root";
        PASSWORD = "12345678";

        try {
            //sqlconnection connection
            sqlConnection = DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            //DB creation
            createDBQRTA = sqlConnection.prepareStatement("create database if not exists QRTA");
            //Creates the database if not exists
            createDBQRTA.executeUpdate();
            if (sqlConnection != null) {
                sqlConnection.close();
            }
            //Connects to database
            dbConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            createTableUsers = dbConnection.prepareStatement(""
                           + "create table if not exists table_driver_license(\n"
                           + "fName varchar(20) not null,\n"
                           + "mName varchar(20) not null,\n"
                           + "lName varchar(20) not null,\n"
                           + "gender varchar(10) not null,\n"
                           + "contactNo varchar(15) not null,\n"
                           + "dob varchar(20) not null,\n"
                           + "address varchar(75) not null,\n"
                           + "licenseType varchar(50) not null,\n"
                           + "expiryDate varchar(20) not null,\n"
                           + "licenseNumber varchar(10) not null)");
            createTableUsers.executeUpdate();
            System.out.println("Connected to Database");
//            insert,update,select and delete statement
            registerLicense = dbConnection.prepareStatement("INSERT into table_driver_license"
                           + "(fName,mName,lName,gender,contactNo,dob,address,licenseType,expiryDate"
                           + ",licenseNumber) values (?,?,?,?,?,?,?,?,?,?)");
            selectLicense = dbConnection.prepareStatement("SELECT * from table_driver_license");
            updateLicense = dbConnection.prepareStatement("UPDATE table_driver_license set fName =?,mName =?,lName=?,"
                           + " gender=?,contactNo=?,dob=?,address=?,licenseType=?,expiryDate=? WHERE licenseNumber=?");
            deleteLicense = dbConnection.prepareStatement("DELETE FROM table_driver_license WHERE licenseNumber=?");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }

    //method to insert into DB
    @Override
    public void registerDriverLicense(DriverLicense license) {
        try {
                registerLicense.setString(1, license.getfName());
                registerLicense.setString(2, license.getmName());
                registerLicense.setString(3, license.getlName());
                registerLicense.setString(4, license.getGender());
                registerLicense.setString(5, license.getContactNo());
                registerLicense.setString(6, license.getDob());
                registerLicense.setString(7, license.getAddress());
                registerLicense.setString(8, license.getLicenseType());
                registerLicense.setString(9, license.getExpiryDate());
                registerLicense.setString(10,license.getLicenseNumber());
                registerLicense.executeUpdate();
                System.out.println("The license has been registered into database");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }

    //method to update into DB
    @Override
    public void updateDriverLicense(DriverLicense license) {
        try {
            registerLicense.setString(1, license.getfName());
            registerLicense.setString(2, license.getmName());
            registerLicense.setString(3, license.getlName());
            registerLicense.setString(4, license.getGender());
            registerLicense.setString(5, license.getContactNo());
            registerLicense.setString(6, license.getDob());
            registerLicense.setString(7, license.getAddress());
            registerLicense.setString(8, license.getLicenseType());
            registerLicense.setString(9, license.getExpiryDate());
            registerLicense.setString(10,license.getLicenseNumber());
            updateLicense.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }

    //method to delete into DB
    @Override
    public void deleteDriverLicense(String license) {
        try {
            deleteLicense.setString(1, license);
            deleteLicense.executeUpdate();
            System.out.println("Maintenance Request deleted from DB");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
        System.out.println("Maintenance Request Deleted From DB");
    }

   @Override
    public ObservableList<DriverLicense> readDriverLicense()  {
    ObservableList<DriverLicense> licenseList = FXCollections.observableArrayList();
        System.out.println("Loading data from database");
        try {
            ResultSet results = selectLicense.executeQuery();
            while (results.next()) {
                licenseList.add(new DriverLicense(
                               results.getString("fName"),
                               results.getString("mName"),
                               results.getString("lName"),
                               results.getString("gender"),
                               results.getString("contactNo"),
                               results.getString("dob"),
                               results.getString("address"),
                               results.getString("licenseType"),
                               results.getString("expiryDate"),
                               results.getString("licenseNumber")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
        System.out.println("Data successfully loaded to database");
        return licenseList;
    }

}