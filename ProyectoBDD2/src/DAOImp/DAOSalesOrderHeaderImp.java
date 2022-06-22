package DAOImp;

import SQL.Conexion;
import Interfaces.DAOSalesOrderHeader;
import SalesClases.SalesOderHeader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DAOSalesOrderHeaderImp extends Conexion implements DAOSalesOrderHeader{

    public DAOSalesOrderHeaderImp() {
        super("sqlserver://192.168.100.166:1433", "Sales", "sa", "So0th$ayer");
    }

    @Override
    public void registrar(SalesOderHeader obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("Insert into "
                    + "Sales.SalesOrderHeader "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?)");
            st.setInt(1,obj.getSalesOrderID());
            st.setShort(2, obj.getRevisionNumber());
            st.setDate(3, obj.getOrderDate());
            st.setDate(4, obj.getDueDate());
            if(obj.getShipDate()!=null)
                st.setDate(5, obj.getShipDate());
            else
                st.setNull(5,Types.TIMESTAMP);
            st.setShort(6, obj.getStatus());
            st.setBoolean(7, obj.getOnlineOrderFlag());
            st.setString(8,obj.getSalesOrderNumber());
            if(obj.getPurchaseOrderNumber()!=null)
                st.setString(9, obj.getPurchaseOrderNumber());
            else
                st.setNull(9,Types.NVARCHAR);
            if(obj.getAccountNumber()!=null)
                st.setString(10, obj.getAccountNumber());
            else
                st.setNull(10,Types.NVARCHAR);
            st.setInt(11, obj.getCustomerID());
            if(obj.getSalesPersonID()!=null)
                st.setInt(12, obj.getSalesPersonID());
            else
                st.setNull(12,Types.INTEGER);
            if(obj.getTerritoryID()!=null)
                st.setInt(13, obj.getTerritoryID());
            else
                st.setNull(13,Types.INTEGER);
            st.setInt(14, obj.getBillToAddressID());
            st.setInt(15, obj.getShipToAdressID());
            st.setInt(16, obj.getShipMethodID());
            if(obj.getCreditCardID()!=null)
                st.setInt(17, obj.getCreditCardID());
            else
                st.setNull(17,Types.INTEGER);
            if(obj.getCreditCardApprovalCode()!=null)
                st.setString(18, obj.getCreditCardApprovalCode());
            else
                st.setNull(18,Types.VARCHAR);
            if(obj.getCurrencyRateID()!=null)
                st.setInt(19, obj.getCurrencyRateID());
            else
                st.setNull(19,Types.INTEGER);
            st.setBigDecimal(20, obj.getSubTotal());
            st.setBigDecimal(21, obj.getTaxAmt());
            st.setBigDecimal(22, obj.getFreight());
            st.setBigDecimal(23, obj.getTotalDue());
            if(obj.getComment()!=null)
                st.setString(24, obj.getComment());
            else
                st.setNull(24,Types.NVARCHAR);
            st.setString(25, obj.getRowguid());
            st.setDate(26, obj.getModifiedDate());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void modificar(SalesOderHeader obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("UPDATE "
                    + "Sales.SalesOrderHeader set "
                    + "RevisionNumber=?,"
                    + "OrderDate=?,"
                    + "DueDate=?,"
                    + "ShipDate=?,"
                    + "Status=?,"
                    + "OnlineOrderFlag=?,"
                    + "SalesOrderNumber=?,"
                    + "PurchaseOrderNumber=?,"
                    + "AccountNumber=?,"
                    + "CustomerID=?,"
                    + "SalesPersonID=?,"
                    + "TerritoryID=?,"
                    + "BillToAddressID=?,"
                    + "ShipToAddressID=?,"
                    + "ShipMethodID=?,"
                    + "CreditCardID=?,"
                    + "CreditCardApprovalCode=?,"
                    + "CurrencyRateID=?,"
                    + "SubTotal=?,"
                    + "TaxAmt=?,"
                    + "Freight=?,"
                    + "TotalDue=?,"
                    + "Comment=?,"
                    + "rowguid=?,"
                    + "ModifiedDate=? "
                    + "where SalesOrderID=?");
            st.setInt(26,obj.getSalesOrderID());
            st.setShort(1, obj.getRevisionNumber());
            st.setDate(2, obj.getOrderDate());
            st.setDate(3, obj.getDueDate());
            if(obj.getShipDate()!=null)
                st.setDate(4, obj.getShipDate());
            else
                st.setNull(4,Types.TIMESTAMP);
            st.setShort(5, obj.getStatus());
            st.setBoolean(6, obj.getOnlineOrderFlag());
            st.setString(7,obj.getSalesOrderNumber());
            if(obj.getPurchaseOrderNumber()!=null)
                st.setString(8, obj.getPurchaseOrderNumber());
            else
                st.setNull(8,Types.NVARCHAR);
            if(obj.getAccountNumber()!=null)
                st.setString(9, obj.getAccountNumber());
            else
                st.setNull(9,Types.NVARCHAR);
            st.setInt(10, obj.getCustomerID());
            if(obj.getSalesPersonID()!=null)
                st.setInt(11, obj.getSalesPersonID());
            else
                st.setNull(11,Types.INTEGER);
            if(obj.getTerritoryID()!=null)
                st.setInt(12, obj.getTerritoryID());
            else
                st.setNull(12,Types.INTEGER);
            st.setInt(13, obj.getBillToAddressID());
            st.setInt(14, obj.getShipToAdressID());
            st.setInt(15, obj.getShipMethodID());
            if(obj.getCreditCardID()!=null)
                st.setInt(16, obj.getCreditCardID());
            else
                st.setNull(16,Types.INTEGER);
            if(obj.getCreditCardApprovalCode()!=null)
                st.setString(17, obj.getCreditCardApprovalCode());
            else
                st.setNull(17,Types.VARCHAR);
            if(obj.getCurrencyRateID()!=null)
                st.setInt(18, obj.getCurrencyRateID());
            else
                st.setNull(18,Types.INTEGER);
            st.setBigDecimal(19, obj.getSubTotal());
            st.setBigDecimal(20, obj.getTaxAmt());
            st.setBigDecimal(21, obj.getFreight());
            st.setBigDecimal(22, obj.getTotalDue());
            if(obj.getComment()!=null)
                st.setString(23, obj.getComment());
            else
                st.setNull(23,Types.NVARCHAR);
            st.setString(24, obj.getRowguid());
            st.setDate(25, obj.getModifiedDate());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void eliminar(SalesOderHeader obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("DELETE from "
                    + "Sales.SalesOrderHeader "
                    + "where SalesOrderID=?");
            st.setString(1, String.valueOf(obj.getSalesOrderID()));
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }

    }

    @Override
    public List<SalesOderHeader> listar() throws Exception {
        List<SalesOderHeader> lista=null;
        try{
            this.conectar();
            lista=new ArrayList();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("select * from "
                    + "Sales.SalesOrderHeader ");
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                SalesOderHeader obj=new SalesOderHeader();
                obj.setSalesOrderID(rs.getInt(1));
                obj.setRevisionNumber(rs.getShort(2));
                obj.setOrderDate(rs.getDate(3));
                obj.setDueDate(rs.getDate(4));
                obj.setShipDate(rs.getDate(5));
                obj.setStatus(rs.getShort(6));
                obj.setOnlineOrderFlag(rs.getBoolean(7));
                obj.setSalesOrderNumber(rs.getString(8));
                obj.setPurchaseOrderNumber(rs.getString(9));
                obj.setAccountNumber(rs.getString(10));
                obj.setCustomerID(rs.getInt(11));
                obj.setSalesPersonID(rs.getInt(12));
                obj.setTerritoryID(rs.getInt(13));
                obj.setBillToAddressID(rs.getInt(14));
                obj.setShipToAdressID(rs.getInt(15));
                obj.setShipMethodID(rs.getInt(16));
                obj.setCreditCardID(rs.getInt(17));
                obj.setCreditCardApprovalCode(rs.getString(18));
                obj.setCurrencyRateID(rs.getInt(19));
                obj.setSubTotal(rs.getBigDecimal(20));
                obj.setTaxAmt(rs.getBigDecimal(21));
                obj.setFreight(rs.getBigDecimal(22));
                obj.setTotalDue(rs.getBigDecimal(23));
                obj.setComment(rs.getString(24));
                obj.setRowguid(rs.getString(25));
                obj.setModifiedDate(rs.getDate(26));
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
    public SalesOderHeader buscar(int ID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nuevaVenta(SalesOderHeader obj) throws Exception {
    this.conectar();
    CallableStatement cstmt =
        this.getConexion().prepareCall(
            "{call dbo.NuevaVenta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
    //1.@OrderDate
        cstmt.setDate(1,obj.getOrderDate());
    //2.@DueDate
        cstmt.setDate(2,obj.getDueDate());
    //3.@ShipDate
        if(obj.getShipDate()!=null)
        cstmt.setDate(3,obj.getShipDate());
        else
        cstmt.setNull(3, Types.DATE);
    //4.@Status
        cstmt.setShort(4,obj.getStatus());
    //5.@OnlineOrderFlag
        cstmt.setBoolean(5,obj.getOnlineOrderFlag());
    //6.@PurchaseOrderNumber
        if(obj.getPurchaseOrderNumber()!=null)
            cstmt.setString(6,obj.getPurchaseOrderNumber());
        else
            cstmt.setNull(6,Types.NULL);
    //7.@AccountNumber
        if(obj.getAccountNumber()!=null)
            cstmt.setString(7,obj.getAccountNumber());
        else
            cstmt.setNull(7, Types.NULL);
    //8.@CustomerID
        cstmt.setInt(8,obj.getCustomerID());
    //9.@SalesPersonID
        if(obj.getSalesPersonID()!=null)
            cstmt.setInt(9,obj.getSalesPersonID());
        else
            cstmt.setNull(9,Types.NULL);
    //10.@TeritoryID
        if(obj.getTerritoryID()!=null)
            cstmt.setInt(10,obj.getTerritoryID());
        else   
            cstmt.setNull(10, Types.NULL);
    //11.@BillAddressID
        cstmt.setInt(11,obj.getBillToAddressID());
    //12.@ShipToAddressID
        cstmt.setInt(12,obj.getShipToAdressID());
    //13.@ShipMethodID
        cstmt.setInt(13,obj.getShipMethodID());
    //14.@CreditCardID
        if(obj.getCreditCardID()!=null)
            cstmt.setInt(14,obj.getCreditCardID());
        else
            cstmt.setNull(14,Types.NULL);
    //15.@CreditCardApprovalCode
        if(obj.getCreditCardApprovalCode()!=null)
            cstmt.setString(15,obj.getCreditCardApprovalCode());
        else
            cstmt.setNull(15,Types.NULL);
    //16.@CurrencyRateID
        if(obj.getCurrencyRateID()!=null)
            cstmt.setInt(16,obj.getCurrencyRateID());
        else
            cstmt.setNull(16, Types.NULL);
    //17.@TaxAmt
        cstmt.setBigDecimal(17,obj.getTaxAmt());
    //18.@Freight
        cstmt.setBigDecimal(18,obj.getFreight());
    //19.@Comment
        if(obj.getComment()!=null)
            cstmt.setString(19,obj.getComment());
        else
            cstmt.setNull(19, Types.NULL);
    //20.@OrderID
        cstmt.registerOutParameter(20,java.sql.Types.INTEGER);
        cstmt.execute();
        // Retornamos numero de orden
        return cstmt.getInt(20);
    }
}
