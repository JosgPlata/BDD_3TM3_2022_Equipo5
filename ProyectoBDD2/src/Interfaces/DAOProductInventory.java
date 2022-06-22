package Interfaces;

import ProductionClases.ProductInventory;
import java.util.List;

public interface DAOProductInventory {
    
    public void registrar(ProductInventory obj)throws Exception;
    public void modificar(ProductInventory obj)throws Exception;
    public void eliminar(ProductInventory obj)throws Exception;
    public ProductInventory buscat(int ID)throws Exception;
    public List<ProductInventory> listar()throws Exception;
    
}
