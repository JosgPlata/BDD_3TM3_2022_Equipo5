package DAODemo;

import DAOImp.DAOSalesOrderDetailImp;
import Interfaces.DAOSalesOrderDetail;
import Proyecto.ProyectoBDD;
import SalesClases.SalesOrderDetail;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOSalesOrderDetailDemo {

    public static void demo() {        
        try {
            //Objeto SalesOrderDetail
            String fechaStr="2012-05-31";
            Date fecha=Date.valueOf(fechaStr);
            SalesOrderDetail sodObj=new SalesOrderDetail(43659,13,"4911-403C-98",
                    (short)3,776,1,
                    BigDecimal.valueOf(202.994),
                    BigDecimal.valueOf(0.00),
                    BigDecimal.valueOf(2024.994),
                    "B207C96D-D9E6-402B-8470-2CC176C42283",
                    fecha);
            //Registro
            DAOSalesOrderDetail daoSOD=new DAOSalesOrderDetailImp();
            daoSOD.registrar(sodObj);
            System.err.println("Registro exitoso");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
            //Actualizacion
            sodObj.setOrderQty((short)14);
            daoSOD.modificar(sodObj);
            System.err.println("Actualizacion exitosa");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
            //Borrado
            daoSOD.eliminar(sodObj);
            System.err.println("Borrado exitoso");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
            //Listado
            for(SalesOrderDetail obj:daoSOD.listar()){
                System.out.println("SalesOrderID="+obj.getSalesOrderID()
                        + ",SalesOrderDetailID="+obj.getSalesOrderDetailID()
                        + ",CarrierTrackingNumber="+obj.getCarrierTrackingNumber()
                        + ",OrderQty="+obj.getOrderQty()
                        + ",ProductID="+obj.getProductID()
                        + ",SpecialOfferID="+obj.getSpecialOfferID()
                        + ",UnitPrice="+obj.getUnitPrice()
                        + ",UnitPriceDiscount="+obj.getUnitPriceDiscount()
                        + ",LineTotal="+obj.getLineTotal()
                        + ",rowguid="+obj.getRowguid()
                        + ",ModifiedDate="+obj.getModifiedDate());
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
            }
        } catch (Exception ex) {
            Logger.getLogger(ProyectoBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
}
