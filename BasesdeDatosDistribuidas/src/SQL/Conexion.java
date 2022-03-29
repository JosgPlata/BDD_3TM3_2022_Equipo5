/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

/**
 *
 * @author chenc
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con = null;
    private final String url = "jdbc:sqlserver://localhost:1433;database=AdventureWorks2019;trustServerCertificate=true;";
    
    public Conexion() {
        try 
        {
            //con = DriverManager.getConnection(url, usuario, contraseña);
            con = DriverManager.getConnection(url);
            if (con != null) 
            {
                System.out.println("Conexión a base de datos Cineteca listo");
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error de Conexion" + e.getMessage());
        } 
    }

    public Connection Conectar() 
    {
        return con;
    }

    public void desconectar() 
    {
        con = null;
        System.out.println("Conexion terminada");
    }
    
    public static Connection getConexion(){
        
        String url = "jdbc:sqlserver://localhost:1433;"
                + "database=AdventureWorks2019;" 
                + "user=sa;"
                + "password=123456;"
                + "trustServerCertificate=true;";
        
        try{
            Connection con = DriverManager.getConnection(url);
            System.out.println("Se conecto");
            return con;
        } catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
}
