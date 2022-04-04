package Proyecto;

import DAOImp.DAOSalesOrderDetailImp;
import java.sql.*;
import SQL.Conexion;
import SalesClases.*;
import DAOImp.*;
import DAODemo.*;
import Interfaces.DAOSalesOrderDetail;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProyectoBDD {

    public static void main(String[] args) throws SQLException, ParseException{
        DAOSalesOrderDetailDemo.demo();
    }
    
}

    