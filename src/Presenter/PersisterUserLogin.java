
package Presenter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import Model.User;
import javax.swing.JOptionPane;
/**
 *
 * @author Mary Tom
 * Created 4th May 2021
 * This file is for creating a new database named mentcare
 * Two tables will be created, user and patient.
 * Methods are provided to add users, patients and select patients.
 * You should add the jdbc connector to your project library for this to work.
 * Make sure your MySql server is running before running this program
 * You can find the server in your services within NetBeans and right-click 
 * and start.
 */
public class PersisterUserLogin {
    private  final String MYSQL_URL;
    private  final String DB_URL;
    private  final String USERNAME;
    private  final String PASSWORD;
    private Connection sqlConnection;
    private Connection dbConnection;
    private PreparedStatement createDBQRTA,createTableUser,insertUser,selectUser,deleteUser;
  
    private User user;
    private String u="";
    private String p="";
    public PersisterUserLogin (){
        this.user = new User();
        MYSQL_URL = "jdbc:mysql://localhost:3306";
        DB_URL = MYSQL_URL + "/QRTA";
        USERNAME = "root";
        PASSWORD = "12345678";
        
        try {
            //Connects to the SQL instance
            sqlConnection = DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD); 
            //Creates the database if not exists
            createDBQRTA = sqlConnection.prepareStatement("create database if not exists QRTA");
            createDBQRTA.executeUpdate();
            if (sqlConnection != null) {
               sqlConnection.close();
            }
           //Connects to database
            dbConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); 
            createTableUser = dbConnection.prepareStatement(""
                    + "create table if not exists user(\n"
                    + "userID int not null auto_increment,\n"
                    + "username varchar(50) not null,\n"
                    + "password varchar(10) not null,\n"
                    + "primary key (userID))");
            createTableUser.executeUpdate();
//            insertUser = dbConnection.prepareStatement("insert into user"
//                    + "(username, password)"
//                    + "values (?,?)");
//            insertUser = dbConnection.prepareStatement("if not exists (select * from user where username = 'admin' and password = 'pwdpwd') "+
//            "begin insert into user (username, password) values (?,?)");  
            insertUser = dbConnection.prepareStatement("INSERT INTO user (username,password) \n" +
            "SELECT 'admin', 'pwdpwd'\n" +
            "FROM   dual    \n" +
            "WHERE  NOT EXISTS (SELECT * FROM user WHERE  username = 'admin' AND password = 'pwdpwd');");
            selectUser = dbConnection.prepareStatement("select username,password from user where username='admin' and password='pwdpwd'");
            deleteUser = dbConnection.prepareStatement("delete from user");
        }catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }
    
    public void addUsers(User user) throws SQLException  {
        this.user = user;
        insertUser.executeUpdate();
    }
    
    public boolean loggedIn(String username, String password) throws SQLException{
        ResultSet rs = selectUser.executeQuery();
        System.out.println(selectUser.toString());

        while(rs.next()){
            u = rs.getString("username");
            p = rs.getString("password");
        }
        System.out.println("username: "+u);
        System.out.println("password: "+ p);
        if(username.equals(u) && password.equals(p)){
            System.out.println("LogIn Success");
            return true;
        }else{
            System.out.println("LogIn Fail");
            return false;
        }
    }
    
    public void deleteUser() throws SQLException{
        deleteUser.executeUpdate();
        System.out.println("delete success");
    }

}
