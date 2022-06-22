package DAODemo;

import DAOImp.DAOSpecialOfferProductImp;
import Proyecto.ProyectoBDD;
import SalesClases.SpecialOfferProduct;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class DAOSpecialOfferProductDemo {

    public static void demo(){
        try{
        //Objeto Customer
            Date fechaDt=Date.valueOf("2022-04-04");
            SpecialOfferProduct sopObj=
                new SpecialOfferProduct(1,500,"2981C98B-A18F-4153-A6A2-EC453CB130CE",fechaDt);
        //Registro
            DAOSpecialOfferProductImp daoCUST=new DAOSpecialOfferProductImp();
            daoCUST.registrar(sopObj);
            System.err.println("Registro exitoso");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Actualizacion
            sopObj.setRowguid("A497D0BD-5CF4-46CB-A596-FAA6B28932F7");
            fechaDt=Date.valueOf("2020-04-04");
            sopObj.setModifiedDate(fechaDt);
            daoCUST.modificar(sopObj);
            System.err.println("Actualizacion exitosa");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Borrado
            daoCUST.eliminar(sopObj);
            System.err.println("Borrado exitoso");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Listado
            for(SpecialOfferProduct obj:daoCUST.listar()){
                System.out.println("SpecialOfferID:"+obj.getSpecialOfferID()
                        +", ProductID="+obj.getProductID()
                        +", rowguid="+obj.getRowguid()
                        +", ModifiedDate="+obj.getModifiedDate());
            System.out.println("Introduzca s para terminar o cualquier otra tecla para continuar...");
            if("s".equals(new Scanner(System.in).nextLine()))
                break;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProyectoBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
