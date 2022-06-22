package DAOImp;

import SQL.Conexion;
import Interfaces.DAOCustomer;
import SalesClases.Customer;
import SalesClases.SalesOderHeader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

public class DAOCustomerImp extends Conexion implements DAOCustomer {

    public DAOCustomerImp() {
        super("sqlserver://192.168.100.166:1433", "Sales", "sa", "So0th$ayer");
    }
       
    @Override
    public void registrar(Customer obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("Insert into "
                    + "Sales.Customer "
                    + "values(?,?,?,?,?,?,?)");
            st.setInt(1, obj.getCustomerID());
            if(obj.getPersonID()!=null)
                st.setInt(2, obj.getPersonID());
            else
                st.setNull(2,Types.INTEGER);
            if(obj.getStoreID()!=null)
                st.setInt(3, obj.getStoreID());
            else
                st.setNull(3, Types.INTEGER);
            if(obj.getTerritoryID()!=null)
                st.setInt(4, obj.getTerritoryID());
            else
                st.setNull(4, Types.INTEGER);
            st.setString(5, obj.getAccountNumber());
            st.setString(6, obj.getRowguid());
            st.setDate(7,obj.getModifiedDate());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }    
    }

    @Override
    public void modificar(Customer cst) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("UPDATE "
                    + "Sales.Customer set "
                    + "PersonID=?,"
                    + "StoreID=?,"
                    + "TerritoryID=?,"
                    + "AccountNumber=?,"
                    + "ModifiedDate=? "
                    + "where CustomerID=?");
            if(cst.getPersonID()!=null)
                st.setInt(1, cst.getPersonID());
            else
                st.setNull(1,Types.INTEGER);
            if(cst.getStoreID()!=null)
                st.setInt(2, cst.getStoreID());
            else
                st.setNull(2, Types.INTEGER);
            if(cst.getTerritoryID()!=null)
                st.setInt(3, cst.getTerritoryID());
            else
                st.setNull(3, Types.INTEGER);
            st.setString(4, cst.getAccountNumber());
            Date fecha= new Date();
            st.setDate(5, new java.sql.Date(fecha.getTime()));
            st.setInt(6, cst.getCustomerID());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Customer obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("DELETE from "
                    + "Sales.Customer "
                    + "where CustomerID=?");
            st.setInt(1, obj.getCustomerID());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }        
    }
    
    @Override
    public Customer buscar(int ID)throws Exception{
        Customer cliente=new Customer();
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("select * from "
                    + "Sales.customer "
                    + "WHERE customerID=?");
            st.setInt(1, ID);
            ResultSet rs=st.executeQuery();
            rs.next();
            cliente.setCustomerID(rs.getInt(1));
            cliente.setPersonID(rs.getInt(2));
            cliente.setStoreID(rs.getInt(3));
            cliente.setTerritoryID(rs.getInt(4));
            cliente.setAccountNumber(rs.getString(5));
            cliente.setRowguid(rs.getString(6));
            cliente.setModifiedDate(rs.getDate(7));
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    return cliente;
    }

    @Override
    public List<Customer> listar() throws Exception {
        List<Customer> lista=null;
        try{
            this.conectar();
            lista=new ArrayList();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("select * from "
                    + "Sales.customer ");
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Customer obj=new Customer();
                obj.setCustomerID(rs.getInt(1));
                obj.setPersonID(rs.getInt(2));
                obj.setStoreID(rs.getInt(3));
                obj.setTerritoryID(rs.getInt(4));
                obj.setAccountNumber(rs.getString(5));
                obj.setRowguid(rs.getString(6));
                obj.setModifiedDate(rs.getDate(7));
                lista.add(obj);
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;        
    }
        
}
    
