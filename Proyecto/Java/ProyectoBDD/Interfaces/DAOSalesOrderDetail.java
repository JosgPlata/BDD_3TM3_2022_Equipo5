package Interfaces;

import SalesClases.SalesOrderDetail;
import java.util.List;

public interface DAOSalesOrderDetail {
    
    public void registrar(SalesOrderDetail obj)throws Exception;
    public void modificar(SalesOrderDetail obj)throws Exception;
    public void eliminar(SalesOrderDetail obj)throws Exception;
    public List<SalesOrderDetail> listar()throws Exception;
}
