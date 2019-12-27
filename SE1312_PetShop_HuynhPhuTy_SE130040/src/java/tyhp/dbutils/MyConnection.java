/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.dbutils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ACER
 */
public class MyConnection implements Serializable{
    public static Connection makeConnection() throws Exception{
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=PetServiceSystem", "sa", "tyhuynh99");
        return conn;
    }
            
}
