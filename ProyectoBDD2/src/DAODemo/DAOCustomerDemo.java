package DAODemo;

import DAOImp.DAOCustomerImp;
import Proyecto.ProyectoBDD;
import SalesClases.Customer;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOCustomerDemo {   

    public static void demo(){
        try{
        //Objeto Customer
            Date fechaDt=Date.valueOf("2022-04-04");
            Customer customerObj=
                new Customer(30119,1970,1970,10,
                    "AW00030107","AC07DA4A-9FB0-4832-8219-5B6D43DD13D8",fechaDt);
        //Registro
            DAOCustomerImp daoCUST=new DAOCustomerImp();
            daoCUST.registrar(customerObj);
            System.err.println("Registro exitoso");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Actualizacion
            customerObj.setPersonID(1950);
            customerObj.setStoreID(1950);
            fechaDt=Date.valueOf("2020-04-04");
            fechaDt.setTime(fechaDt.getTime());
            customerObj.setModifiedDate(fechaDt);
            daoCUST.modificar(customerObj);
            System.err.println("Actualizacion exitosa");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Borrado
            daoCUST.eliminar(customerObj);
            System.err.println("Borrado exitoso");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Listado
            for(Customer obj:daoCUST.listar()){
                System.out.println("CustomerID:"+obj.getCustomerID()
                        +", PersonID="+obj.getPersonID()
                        +", StoreID="+obj.getStoreID()
                        +", TerritoryID="+obj.getTerritoryID()
                        +", AccountNumber="+obj.getAccountNumber()
                        +", rowguid="+obj.getRowguid()
                        +", ModifiedDate="+obj.getModifiedDate());
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
            }
        } catch (Exception ex) {
            Logger.getLogger(ProyectoBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
