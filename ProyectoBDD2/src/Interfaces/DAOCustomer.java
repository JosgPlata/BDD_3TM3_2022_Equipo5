package Interfaces;

import SalesClases.Customer;
import java.util.List;

public interface DAOCustomer {
    
    public void registrar(Customer obj)throws Exception;
    public void modificar(Customer obj)throws Exception;
    public void eliminar(Customer obj)throws Exception;
    public Customer buscar(int ID)throws Exception;
    public List<Customer> listar()throws Exception;
    
}
