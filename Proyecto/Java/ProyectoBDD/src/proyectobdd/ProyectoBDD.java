package proyectobdd;
import java.sql.*;
import SQL.Conexion;
public class ProyectoBDD {

    public static void main(String[] args) throws SQLException 
    {
      Conexion conx= new Conexion();
        Statement st = conx.getConexion().createStatement();
        ResultSet res = null;
        String query = "";
        // lets , print what we have updated in the table
            query = "select top 1 * from sales.SalesOrderDetail;";
            res = st.executeQuery(query);
            System.out.println("Consulta de prueba en la tabla SalesOrderDetail:");
            while (res.next())
                System.out.print(res.getString(1)+","
                        + res.getString(2)+","+res.getString(3)+","
                        + res.getString(4));
            System.out.println();
    }
    
}

    