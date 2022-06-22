package Interfaces;

import SalesClases.SalesOderHeader;
import java.util.List;

public interface DAOSalesOrderHeader {
    
    public void registrar(SalesOderHeader obj)throws Exception;
    public void modificar(SalesOderHeader obj)throws Exception;
    public void eliminar(SalesOderHeader obj)throws Exception;
    public int nuevaVenta(SalesOderHeader obj)throws Exception;
    public SalesOderHeader buscar(int ID)throws Exception;
    public List<SalesOderHeader> listar()throws Exception;
}
