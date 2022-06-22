package DAOImp;

import SQL.Conexion;
import Interfaces.DAOSpecialOfferProduct;
import SalesClases.SpecialOfferProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOSpecialOfferProductImp extends Conexion implements DAOSpecialOfferProduct{

    public DAOSpecialOfferProductImp() {
        super("sqlserver://192.168.100.166:1433", "Sales", "sa", "So0th$ayer");
    }
    
    @Override
    public void registrar(SpecialOfferProduct obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("Insert into "
                    + "Sales.SpecialOfferProduct "
                    + "values(?,?,?,?)");
            st.setInt(1, obj.getSpecialOfferID());
            st.setInt(2, obj.getProductID());
            st.setString(3, obj.getRowguid());
            st.setDate(4, obj.getModifiedDate());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        } 
    }

    @Override
    public void modificar(SpecialOfferProduct obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("UPDATE "
                    + "Sales.SpecialOfferProduct set "
                    + "rowguid=?,"
                    + "ModifiedDate=? "
                    + "where SpecialOfferID=? "
                    + "and ProductID=?");
            st.setString(1, obj.getRowguid());
            Date fecha= new Date();
            st.setDate(2, new java.sql.Date(fecha.getTime()));
            st.setInt(3, obj.getSpecialOfferID());
            st.setInt(4, obj.getProductID());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }        
    }

    @Override
    public void eliminar(SpecialOfferProduct obj) throws Exception {
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("DELETE from "
                    + "Sales.SpecialOfferProduct "
                    + "where SpecialOfferID=? "
                    + "and ProductID=?");
            st.setInt(1, obj.getSpecialOfferID());
            st.setInt(2, obj.getProductID());
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }        
    }

    @Override
    public SpecialOfferProduct buscar(int soID, int pID) throws Exception {
        SpecialOfferProduct sop=new SpecialOfferProduct();
        try{
            this.conectar();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("SELECT * from "
                    + "Sales.SpecialOfferProduct "
                    + "WHERE SpecialOfferID=? "
                    + "AND ProductID=?");
            st.setInt(1, soID);
            st.setInt(2, pID);
            ResultSet rs=st.executeQuery();
            rs.next();
            sop.setSpecialOfferID(rs.getInt(1));
            sop.setProductID(rs.getInt(2));
            sop.setRowguid(rs.getString(3));
            sop.setModifiedDate(rs.getDate(4));
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    return sop;
    }
    
    @Override
    public List<SpecialOfferProduct> listar() throws Exception {
        List<SpecialOfferProduct> lista=null;
        try{
            this.conectar();
            lista=new ArrayList();
            PreparedStatement st;
            st=this.getConexion().prepareStatement("select * from "
                    + "Sales.SpecialOfferProduct ");
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                SpecialOfferProduct obj=new SpecialOfferProduct();
                obj.setSpecialOfferID(rs.getInt(1));
                obj.setProductID(rs.getInt(2));
                obj.setRowguid(rs.getString(3));
                obj.setModifiedDate(rs.getDate(4));
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
