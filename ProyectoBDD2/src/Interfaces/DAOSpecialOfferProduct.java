package Interfaces;

import SalesClases.SpecialOfferProduct;
import java.util.List;

public interface DAOSpecialOfferProduct {
    
    public void registrar(SpecialOfferProduct obj)throws Exception;
    public void modificar(SpecialOfferProduct obj)throws Exception;
    public void eliminar(SpecialOfferProduct obj)throws Exception;
    public SpecialOfferProduct buscar(int specialOfferID,int productID)throws Exception;
    public List<SpecialOfferProduct> listar()throws Exception;
}
