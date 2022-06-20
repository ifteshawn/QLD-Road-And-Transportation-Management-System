//AUTHOR NAME: Tedtya RADY
//Student ID: 12139186
package Presenter;

import Model.Vehicle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersisterVehicle implements IPersistVehicle {

//    variable and databases delcaration
    private final String MYSQL_URL;
    private final String DB_URL;
    private final String USERNAME;
    private final String PASSWORD;
    private Connection sqlConnection;
    private Connection dbConnection;
    private PreparedStatement createDBQRTA,createTableVehicle;
    private PreparedStatement registerVehicle, selectVehicle,
                   updateVehicle, deleteVehicle,deleteAllVehicle;
//    private DriverLicense vehicle;
    
    public PersisterVehicle() {

        //database variable values
        MYSQL_URL = "jdbc:mysql://localhost:3306";
        DB_URL = MYSQL_URL + "/QRTA";
        USERNAME = "root";
        PASSWORD = "12345678";
        
//        this.requestList = new LinkedList<>();
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
            createTableVehicle = dbConnection.prepareStatement(""
                           + "create table if not exists table_vehicle(\n"
                           + "plateNum varchar(20) not null,\n"
                           + "make varchar(20) not null,\n"
                           + "type varchar(20) not null,\n"
                           + "year varchar(10) not null,\n"
                           + "color varchar(15) not null,\n"
                           + "engineNum varchar(20) not null,\n"
                           + "licenseNumber varchar(10) not null)");
            createTableVehicle.executeUpdate();
            System.out.println("Connected to Database");
//            insert,update,select and delete statement
            registerVehicle = dbConnection.prepareStatement("INSERT into table_vehicle"
                           + "(plateNum,make,type,year,color,engineNum,licenseNumber)"
                           + "values (?,?,?,?,?,?,?)");
            selectVehicle = dbConnection.prepareStatement("SELECT * from table_vehicle");
            updateVehicle = dbConnection.prepareStatement("UPDATE table_vehicle set plateNum =?,make =?,type=?,year=?,color=?,engineNum=?\n" +
            "WHERE licenseNumber=?");
            deleteVehicle = dbConnection.prepareStatement("DELETE FROM table_vehicle WHERE licenseNumber=?");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }

    //method to insert into DB
    @Override
    public void registerVehicle(Vehicle vehicle) {
        try {
                registerVehicle.setString(1, vehicle.getPlateNum());
                registerVehicle.setString(2, vehicle.getMake());
                registerVehicle.setString(3, vehicle.getType());
                registerVehicle.setString(4, vehicle.getYear());
                registerVehicle.setString(5, vehicle.getColor());
                registerVehicle.setString(6, vehicle.getEngineNum());
                registerVehicle.setString(7, vehicle.getLicenseNumber());
                registerVehicle.executeUpdate();
                System.out.println("The Vehicle has been registered into database");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }

    //method to update into DB
    @Override
    public void updateVehicle(Vehicle vehicle) {
        try {
            updateVehicle.setString(1, vehicle.getPlateNum());
            updateVehicle.setString(2, vehicle.getMake());
            updateVehicle.setString(3, vehicle.getType());
            updateVehicle.setString(4, vehicle.getYear());
            updateVehicle.setString(5, vehicle.getColor());
            updateVehicle.setString(6, vehicle.getEngineNum());
            updateVehicle.setString(7, vehicle.getLicenseNumber());
            updateVehicle.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }

    //method to delete into DB
    @Override
    public void deleteVehicle(String licenseNumber) {
        try {
            deleteVehicle.setString(1, licenseNumber);
            deleteVehicle.executeUpdate();
            System.out.println("Vehicle deleted from DB");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }
   
    @Override
    public ObservableList<Vehicle> readVehicle()  {
    ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();
        System.out.println("Loading data from database");
        try {
            ResultSet results = selectVehicle.executeQuery();
            while (results.next()) {
                vehicleList.add(new Vehicle(
                               results.getString("plateNum"),
                               results.getString("make"),
                               results.getString("type"),
                               results.getString("year"),
                               results.getString("color"),
                               results.getString("engineNum"),
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
        return vehicleList;
    }

}