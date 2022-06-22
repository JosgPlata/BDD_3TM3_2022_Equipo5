package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    private Connection con = null;
    private String url;
    private String instancia;
    private String database;
    private String usuario;
    private String contrasena;
    
    public Conexion(String instancia, String database,
                    String usuario, String contrasena){
        this.instancia=instancia;
        this.database=database;
        this.usuario=usuario;
        this.contrasena=contrasena;
        if(instancia.contains("sqlserver"))
        url="jdbc:"+instancia+";database="+database+
                ";trustServerCertificate=true;";
        else{
        url="jdbc:"+instancia+"/"+database;
        }
    }

    public void conectar(){
        try{
            con = DriverManager.getConnection(url,usuario,contrasena);
            if (con != null) 
                System.out.println("Conexion exitosa con el servidor");
        } 
        catch (SQLException e){
            System.out.println("Error de Conexion: " + e.getMessage());
        } 
    }

    public void desconectar() throws SQLException{
        if(con!=null)
            if(!con.isClosed())
                con.close();
    }
    
    public Connection getConexion(){
            return con;
    }
}