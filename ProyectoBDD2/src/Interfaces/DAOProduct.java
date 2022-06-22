package Interfaces;

import ProductionClases.Product;
import java.util.List;

public interface DAOProduct {
    
    public void registrar(Product obj)throws Exception;
    public void modificar(Product obj)throws Exception;
    public void eliminar(Product obj)throws Exception;
    public Product buscat(int ID)throws Exception;
    public List<Product> listar()throws Exception;
    
}
