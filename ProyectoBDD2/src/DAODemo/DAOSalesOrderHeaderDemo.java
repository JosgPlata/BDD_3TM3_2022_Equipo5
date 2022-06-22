package DAODemo;

import DAOImp.DAOSalesOrderHeaderImp;
import Proyecto.ProyectoBDD;
import SalesClases.SalesOderHeader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOSalesOrderHeaderDemo {   
    
    public static void demo(){        
        try{
        //Objeto SalesOrderDetail
            Date fechaDt=Date.valueOf("2022-04-04");
            SalesOderHeader sohObj=
                    new SalesOderHeader(43658,(short)100,fechaDt,fechaDt,
            fechaDt,(short)5,false,"SO43659","PO522145787","10-4020-000676",29825,
            279,5,985,985,5,16281,"105041Vi84182",null,
            BigDecimal.valueOf(20565.6206),BigDecimal.valueOf(1971.5149),
            BigDecimal.valueOf(616.0984),BigDecimal.valueOf(23153.2339),
            null,"79B65321-39CA-4115-9CBA-8FE0903E12E6",fechaDt);
        //Registro
            DAOSalesOrderHeaderImp daoSOH=new DAOSalesOrderHeaderImp();
            daoSOH.registrar(sohObj);
            System.err.println("Registro exitoso");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Actualizacion
            sohObj.setRevisionNumber((short)50);
            fechaDt=Date.valueOf("2022-04-04");
            fechaDt.setTime(fechaDt.getTime());
            sohObj.setOrderDate(fechaDt);
            daoSOH.modificar(sohObj);
            System.err.println("Actualizacion exitosa");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Borrado
            daoSOH.eliminar(sohObj);
            System.err.println("Borrado exitoso");
            System.out.println("Presione una tecla para continuar...");
            new java.util.Scanner(System.in).nextLine();
        //Listado
            for(SalesOderHeader obj:daoSOH.listar()){
                System.out.println("SalesOrderID="+obj.getSalesOrderID()
                        + ",SalesOrderID="+obj.getSalesOrderID()
                    + ",RevisionNumber="+obj.getRevisionNumber()
                    + ",OrderDate="+obj.getOrderDate()
                    + ",DueDate="+obj.getDueDate()
                    + ",ShipDate="+obj.getShipDate()
                    + ",Status="+obj.getStatus()
                    + ",OnlineOrderFlag="+obj.getOnlineOrderFlag()
                    + ",SalesOrderNumber="+obj.getSalesOrderNumber()
                    + ",PurchaseOrderNumber="+obj.getPurchaseOrderNumber()
                    + ",AccountNumber="+obj.getAccountNumber()
                    + ",CustomerID="+obj.getCustomerID()
                    + ",SalesPersonID="+obj.getSalesPersonID()
                    + ",TerritoryID="+obj.getTerritoryID()
                    + ",BillToAddressID="+obj.getBillToAddressID()
                    + ",ShipToAddressID="+obj.getShipToAdressID()
                    + ",ShipMethodID="+obj.getShipMethodID()
                    + ",CreditCardID="+obj.getCreditCardID()
                    + ",CreditCardApprovalCode="+obj.getCreditCardApprovalCode()
                    + ",CurrencyRateID="+obj.getCurrencyRateID()
                    + ",SubTotal="+obj.getSubTotal()
                    + ",TaxAmt="+obj.getTaxAmt()
                    + ",Freight="+obj.getFreight()
                    + ",TotalDue="+obj.getTotalDue()
                    + ",Comment="+obj.getComment()
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
