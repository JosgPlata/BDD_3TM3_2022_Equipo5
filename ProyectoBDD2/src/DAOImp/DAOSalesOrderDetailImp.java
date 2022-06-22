package DAOImp;

import SQL.Conexion;
import Interfaces.DAOSalesOrderDetail;
import SalesClases.SalesOrderDetail;
import java.sql.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DAOSalesOrderDetailImp extends Conexion implements DAOSalesOrderDetail{
    
    public DAOSalesOrderDetailImp() {
        super("sqlserver://192.168.100.166:1433", "Sales", "sa", "So0th$ayer");
    }

    @Override
    public void registrar(SalesOrderDetail obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("Insert into "
                    + "Sales.SalesOrderDetail "
                    + "values(?,?,?,?,?,?,?,?,?,?,?)");
            st.setString(1, String.valueOf(obj.getSalesOrderID()));
            st.setString(2, String.valueOf(obj.getSalesOrderDetailID()));
            st.setString(3, String.valueOf(obj.getCarrierTrackingNumber()));
            st.setString(4, String.valueOf(obj.getOrderQty()));
            st.setString(5, String.valueOf(obj.getProductID()));
            st.setString(6, String.valueOf(obj.getSpecialOfferID()));
            st.setString(7, String.valueOf(obj.getUnitPrice()));
            st.setString(8, String.valueOf(obj.getUnitPriceDiscount()));
            st.setString(9, String.valueOf(obj.getLineTotal()));
            st.setString(10, String.valueOf(obj.getRowguid()));
            st.setDate(11, obj.getModifiedDate());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void modificar(SalesOrderDetail obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("UPDATE "
                    + "Sales.SalesOrderDetail set "
                    + "CarrierTrackingNumber=?, "
                    + "OrderQty=?, "
                    + "ProductID=?, "
                    + "SpecialOfferID=?, "
                    + "UnitPrice=?, "
                    + "UnitPriceDiscount=?, "
                    + "LineTotal=?, "
                    + "rowguid=?, "
                    + "ModifiedDate=? "
                    + "where SalesOrderID=? "
                    + "and SalesOrderDetailID=? ");
            st.setString(1, String.valueOf(obj.getCarrierTrackingNumber()));
            st.setString(2, String.valueOf(obj.getOrderQty()));
            st.setString(3, String.valueOf(obj.getProductID()));
            st.setString(4, String.valueOf(obj.getSpecialOfferID()));
            st.setString(5, String.valueOf(obj.getUnitPrice()));
            st.setString(6, String.valueOf(obj.getUnitPriceDiscount()));
            st.setString(7, String.valueOf(obj.getLineTotal()));
            st.setString(8, String.valueOf(obj.getRowguid()));
            st.setDate(9, obj.getModifiedDate());
            st.setString(10, String.valueOf(obj.getSalesOrderID()));
            st.setString(11, String.valueOf(obj.getSalesOrderDetailID()));
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void eliminar(SalesOrderDetail obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("DELETE from "
                    + "Sales.SalesOrderDetail "
                    + "where SalesOrderID=? "
                    + "and SalesOrderDetailID=?");
            st.setString(1, String.valueOf(obj.getSalesOrderID()));
            st.setString(2, String.valueOf(obj.getSalesOrderDetailID()));
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public List<SalesOrderDetail> listar() throws Exception {
        List<SalesOrderDetail> lista=null;
        try{
            this.conectar();
            lista=new ArrayList();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("select * from "
                    + "Sales.SalesOrderDetail ");
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                SalesOrderDetail obj=new SalesOrderDetail();
                obj.setSalesOrderID(rs.getInt(1));
                obj.setSalesOrderDetailID(rs.getInt(2));
                obj.setCarrierTrackingNumber(rs.getString(3));
                obj.setOrderQty(rs.getShort(4));
                obj.setProductID(rs.getInt(5));
                obj.setSpecialOfferID(rs.getInt(6));
                obj.setUnitPrice(rs.getBigDecimal(7));
                obj.setUnitPriceDiscount(rs.getBigDecimal(8));
                obj.setLineTotal(rs.getBigDecimal(9));
                obj.setRowguid(rs.getString(10));
                obj.setModifiedDate(rs.getDate(11));
                lista.add(obj);
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;
    }

    @Override
    public SalesOrderDetail buscar(int ID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void detallesOrdenNueva(SalesOrderDetail obj) throws Exception {
    this.conectar();
    CallableStatement cstmt =
        this.getConexion().prepareCall(
            "{call dbo.InsercionSalesOrderDetail(?,?,?,?)}");
    //1.@OrderDate
        cstmt.setInt(1,obj.getSalesOrderID());
    //2.@DueDate
        if(obj.getCarrierTrackingNumber()!=null)
            cstmt.setString(2,obj.getCarrierTrackingNumber());
        else
            cstmt.setNull(2,Types.NULL);
    //3.@OrderQty
        cstmt.setInt(3,obj.getOrderQty());
    //4.@ProductID
        cstmt.setInt(4,obj.getProductID());
    //Ejecutamos el StoreProcedure de los detalles de la venta
        cstmt.execute();
    }
    
}