
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private Connection con = null;
    private final String url="jdbc:sqlserver://192.168.100.101:1433;"
            +"database=Sales;"
            +"user=sa;"
            +"password=So0th$ayer;"
            + "trustServerCertificate=true;";
    
    public Conexion() {
        try 
        {
            //con = DriverManager.getConnection(url, usuario, contraseña);
            con = DriverManager.getConnection(url);
            if (con != null) 
                System.out.println("Conexión a base de datos lista");
        } 
        catch (SQLException e) 
        {
            System.out.println("Error de Conexion: " + e.getMessage());
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
    
    public Connection getConexion(){
            return con;
    }
}