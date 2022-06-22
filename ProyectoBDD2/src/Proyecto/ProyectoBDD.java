package Proyecto;

import SalesClases.*;
import DAOImp.*;
import DAODemo.*;
import java.sql.*;
import java.util.Date;
import SQL.Conexion;
import Interfaces.DAOSalesOrderDetail;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

public class ProyectoBDD {
    
    public static void main(String[] args){
        Scanner sf=new Scanner(System.in);
        int operacionMenu=0;
        SubMenus subMenus=new SubMenus();
        while(operacionMenu!=4){
        //MENU PRINCIPAL
        System.out.println(
            "\nSeleccione las operaciones que quiere hacer:\n"
            + "1.-Operaciones en SalesCustomer\n"
            + "2.-Operaciones en SpecialOfferProduct\n"
            + "3.-Hacer una nueva venta\n"
            + "4.-Salir");
        operacionMenu=sf.nextInt();
        // OPCIONES
        switch(operacionMenu){
            //SUBMENU DE CUSTOMER
            case 1:
            subMenus.menuSalesCustomer();
            break;
            //SUBMENU DE SPECIALOFFER
            case 2:
            subMenus.menuSpecialOfferProduct();
            break;
            //REGISTRO DE LA VENTA
            case 3:
            subMenus.menuVenta();
            break;
            
            case 4:
                System.out.println("Saliendo...");
            break;
           
            default:
            System.out.println("Ingrese una opción valida");
        }
        System.out.flush();
        }
    }

    private static class SubMenus {

    public SubMenus() {}
    
    public void menuSalesCustomer(){
    //Menu de clientes en salesCustomer
    Scanner scn=new Scanner(System.in);
    DAOCustomerImp custImp=new DAOCustomerImp();
    int operacionSM=0;
    int ID;
    Customer cliente;
        System.out.println(
        "\nSeleccione las operaciones que quiere hacer en SalesCustomer:\n"
        + "1.-Buscar\n"
        + "2.-Actualizar\n"
        + "3.-Salir");
        operacionSM=scn.nextInt();
        switch(operacionSM){
            // BUSCAR
            case 1:
                System.out.println("\n1.-Buscar por id\n"
                + "2.-Listar todos los registros");
            operacionSM=scn.nextInt();
            switch(operacionSM){
                //BUSCAMOS POR ID
                case 1:
                System.out.print("Ingrese el Id: ");
                ID=scn.nextInt();
                try {
                //Utilizamos el metodo buscar e imprimos el resultado
                cliente=custImp.buscar(ID);
                System.out.println("El resultado de la consulta es:\n"
                    + "CustomerID:"+cliente.getCustomerID()
                    +", PersonID="+cliente.getPersonID()
                    +", StoreID="+cliente.getStoreID()
                    +", TerritoryID="+cliente.getTerritoryID()
                    +", AccountNumber="+cliente.getAccountNumber()
                    +", rowguid="+cliente.getRowguid()
                    +", ModifiedDate="+cliente.getModifiedDate());
                } catch (Exception ex) {
                    //En caso de error imprimimos el error
                    System.err.println(ex.toString());
                }
                break;
                // Imprimimos todos los registros
                case 2:
                try {
                    for(Customer obj:custImp.listar()){
                System.out.println("CustomerID:"+obj.getCustomerID()
                        +", PersonID="+obj.getPersonID()
                        +", StoreID="+obj.getStoreID()
                        +", TerritoryID="+obj.getTerritoryID()
                        +", AccountNumber="+obj.getAccountNumber()
                        +", rowguid="+obj.getRowguid()
                        +", ModifiedDate="+obj.getModifiedDate());
                }
                } catch (Exception ex) {
                    //En caso de error imprimimos el error
                    System.err.println(ex.toString());
                }
                break;

                default:
                System.out.println("Opcion no valida");
            }
            break;
            //Actualizamos
            case 2:
                //Utilizamos el ID para buscar el registro
                System.out.print("Ingrese el ID del dato a actualizar: ");
                ID=scn.nextInt();
                int upd=0;
                try {
                //Buscamos el registro para actualizar solo datos especificos
                cliente=custImp.buscar(ID);
                // preguntamos por los datos a actualizar
                System.out.print("¿Quiere moficiar el PersonID?\n"
                        + "Si (1), No (otro numero): ");
                if((upd=scn.nextInt())==1){
                    System.out.print("\tIngrese el dato: ");
                    cliente.setPersonID(scn.nextInt());
                }
                System.out.print("¿Quiere moficiar el StoreID?\n"
                        + "Si (1), No (otro numero): ");
                if((upd=scn.nextInt())==1){
                    System.out.print("\tIngrese el dato: ");
                    cliente.setStoreID(scn.nextInt());
                }
                System.out.print("¿Quiere moficiar el TerritoryID?\n"
                        + "Si (1), No (otro numero): ");
                if((upd=scn.nextInt())==1){
                    System.out.print("\tIngrese el dato: ");
                    cliente.setTerritoryID(scn.nextInt());
                }
                System.out.print("¿Quiere moficiar el AccountNumber?\n"
                        + "Si (1), No (otro numero): ");
                if((upd=scn.nextInt())==1){
                    System.out.print("\tIngrese el dato: ");
                    cliente.setAccountNumber(scn.next());
                }
                //Ahora llamamos el metodo para actualizar
                custImp.modificar(cliente);
                } catch (Exception ex) {
                    //En caso de error imprimimos el error
                    System.err.println(ex.toString());
                }
            break;

            default:
            System.out.println("Opcion no valida");
            }        
        }
    
    public void menuSpecialOfferProduct(){
    //Menu de SpecialOfferProduct
    Scanner scn=new Scanner(System.in);
    DAOSpecialOfferProductImp sopImp=new DAOSpecialOfferProductImp();
    int operacionSM=0,soID,pID;
    SpecialOfferProduct sop;
        System.out.println(
        "\nSeleccione las operaciones que quiere hacer en SpecialOfferProduct:\n"
        + "1.-Buscar\n"
        + "2.-Actualizar\n"
        + "3.-Salir");
        operacionSM=scn.nextInt();
        switch(operacionSM){
            // BUSCAR
            case 1:
                System.out.println("\n1.-Buscar por ID\n"
                + "2.-Listar todos los registros");
            operacionSM=scn.nextInt();
            switch(operacionSM){
                //BUSCAMOS POR ID
                case 1:
                System.out.print("Ingrese el SpecialOfferID: ");
                soID=scn.nextInt();
                System.out.print("Ingrese el ProductID: ");
                pID=scn.nextInt();
                try {
                //Utilizamos el metodo buscar e imprimos el resultado
                sop=sopImp.buscar(soID,pID);
                System.out.println("El resultado de la consulta es:"
                        +"SpecialOfferID:"+sop.getSpecialOfferID()
                        +", ProductID="+sop.getProductID()
                        +", rowguid="+sop.getRowguid()
                        +", ModifiedDate="+sop.getModifiedDate());
                } catch (Exception ex) {
                    //En caso de error imprimimos el error
                    System.err.println(ex.toString());
                }
                break;
                // Imprimimos todos los registros
                case 2:
                try {
                    for(SpecialOfferProduct obj:sopImp.listar()){
                System.out.println("SpecialOfferID:"+obj.getSpecialOfferID()
                        +", ProductID="+obj.getProductID()
                        +", rowguid="+obj.getRowguid()
                        +", ModifiedDate="+obj.getModifiedDate());
                }
                } catch (Exception ex) {
                    //En caso de error imprimimos el error
                    System.err.println(ex.toString());
                }
                break;

                default:
                System.out.println("Opcion no valida");
            }
            break;
            //Actualizamos
            case 2:
                //Utilizamos el ID para buscar el registro
                System.out.print("Ingrese el SpecialOfferID del dato a actualizar: ");
                soID=scn.nextInt();
                System.out.print("Ingrese el ProductID del dato a actualizar: ");
                pID=scn.nextInt();
                try {
                //Buscamos el registro para actualizar solo datos especificos
                sop=sopImp.buscar(soID,pID);
                // preguntamos por los datos a actualizar
                System.out.print("¿Quiere moficiar el RowGuid?\n"
                        + "Si (s), No (otra tecla): ");
                if("s".equals(new Scanner(System.in).nextLine())){
                    System.out.print("¿Quiere introducir el dato o generarlo?\n"
                            + "Introducirlo (s), generarlo (otra tecla): ");
                    if("s".equals(new Scanner(System.in).nextLine())){
                        System.out.print("\tIngrese el dato: ");
                        sop.setRowguid(scn.next());
                    }else
                        sop.setRowguid(UUID.randomUUID().toString());
                }
                //Ahora llamamos el metodo para actualizar
                sopImp.modificar(sop);
                } catch (Exception ex) {
                    //En caso de error imprimimos el error
                    System.err.println(ex.toString());
                }
            break;

            default:
            System.out.println("Opcion no valida");
            }        
        }
    
    public int menuVenta(){
        SalesOderHeader soh=new SalesOderHeader();
        DAOSalesOrderHeaderImp sohImp=new DAOSalesOrderHeaderImp();
        Scanner scn=new Scanner(System.in);
        String fechastr;
        int ordenID=0;
        String agrMas="s";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fecha=new Date(); 
        soh.setOrderDate(new java.sql.Date(fecha.getTime()));//Fecha de la orden
    //Confirmamos que sea en linea la compra
        System.out.print("\n¿La compra es en linea?(Si->s/No->Otra tecla): ");
        if("s".equals(new Scanner(System.in).nextLine())){
            soh.setOnlineOrderFlag(true);//Online order flag
            soh.setSalesPersonID(null);// Id del vendedor
        }else{
            soh.setOnlineOrderFlag(false);//Online order flag
            System.out.println("\nIngrese los siguientes datos:\n");
            System.out.print("ID del vendedor: ");
            soh.setSalesPersonID(scn.nextInt());// Id del vendedor
        }
    //Id del cliente*
        System.out.print("Ingrese el ID del cliente: ");
        soh.setCustomerID(scn.nextInt());
    //Dirección de facturación (BillToAddress)*
        System.out.print("Ingrese el ID de la direccion de facturacion: ");
        soh.setBillToAddressID(scn.nextInt());
    //Dirección de envío*
        System.out.print("Ingrese el ID de la direccion de envio: ");
        soh.setShipToAdressID(scn.nextInt());
    //Metodo de envío
        System.out.print("Ingrese el ID del metodo de envío: ");
        soh.setShipMethodID(scn.nextInt());
    //Fecha de entrega
        System.out.print("Ingrese la fecha de entrega (AAAA-MM-DD): ");
        scn.nextLine();
        fechastr=scn.nextLine();
        try {fecha = sdf.parse(fechastr);
        } catch (ParseException ex) {
           System.out.println("Error en el formato de la fecha");
        }
        soh.setDueDate(new java.sql.Date(fecha.getTime()));
    //Fecha de envío
        System.out.print("¿Ingresar fecha de envío?(Si->s/No->Otra tecla): ");
        if("s".equals(new Scanner(System.in).nextLine())){
            System.out.print("Ingrese la fecha de envío (AAAA-MM-DD): ");
        try {fecha = sdf.parse(scn.nextLine());
        } catch (ParseException ex) {
           System.out.println("Error en el formato de la fecha");
        }
        soh.setShipDate(new java.sql.Date(fecha.getTime()));
        }else
        soh.setShipDate(null);
    //Territorio de la venta (TerritoryID)
        System.out.print("¿Ingresar el territorio de la orden?(Si->s/No->Otra tecla): ");
        if("s".equals(new Scanner(System.in).nextLine())){
            System.out.print("Ingrese el ID del territorio(1-10): ");
            soh.setTerritoryID(scn.nextInt());
        }else
            soh.setTerritoryID(null);
    //Tarjeta de credito (CreditCardID, CreditApprovalCode)
        System.out.print("¿Ingresar tarjeta de credito?(Si->s/No->Otra tecla): ");
        if("s".equals(new Scanner(System.in).nextLine())){
            System.out.print("Ingrese el ID de la tarjeta: ");
            soh.setCreditCardID(scn.nextInt());
            System.out.print("Ingrese codigo de aprovacion: ");
            soh.setCreditCardApprovalCode(scn.next());
        }else{
            soh.setCreditCardID(null);
            soh.setCreditCardApprovalCode(null);
        }
    // Tipo de cambio(CurrencyRateID)
        System.out.print("¿Tipo de cambio?(Si->s/No->Otra tecla): ");
        if("s".equals(new Scanner(System.in).nextLine())){
            System.out.print("Ingrese el ID del tipo de cambio: ");
            soh.setCurrencyRateID(scn.nextInt());
        }else
            soh.setCurrencyRateID(null);
    // Cargos de impuestos y costes de envío (TaxAmt)
        System.out.print("¿Agregar importe de impuesto?(Si->s/No->Otra tecla): ");
        if("s".equals(new Scanner(System.in).nextLine())){
            System.out.print("Ingrese la cantidad total de importe: ");
            soh.setTaxAmt(scn.nextBigDecimal());
        }else
            soh.setTaxAmt(BigDecimal.valueOf(0));
    //Cargo de envío (Freight)
        System.out.print("¿Agregar el cargo del envío?(Si->s/No->Otra tecla): ");
        if("s".equals(new Scanner(System.in).nextLine())){
            System.out.print("Ingrese la cantidad total de importe: ");
            soh.setFreight(scn.nextBigDecimal());
        }else
            soh.setFreight(BigDecimal.valueOf(0));
    //Comentario (comment)
        System.out.print("¿Agregar un comentario?(Si->s/No->Otra tecla): ");
        if("s".equals(new Scanner(System.in).nextLine())){
            System.out.print("Escriba el ecomentario(128 caracteres maximo): ");
            soh.setComment(scn.next());
        }else
            soh.setComment(null);
    //Status    
        soh.setStatus((short)1);
    //Datos del SalesOrderHeader
       /* System.out.println(soh.getOrderDate());
        System.out.println(soh.getDueDate());
        System.out.println(soh.getShipDate());
        System.out.println(soh.getStatus());
        System.out.println(soh.getOnlineOrderFlag());
        System.out.println(soh.getPurchaseOrderNumber());
        System.out.println(soh.getAccountNumber());
        System.out.println(soh.getCustomerID());
        System.out.println(soh.getSalesPersonID());
        System.out.println(soh.getTerritoryID());
        System.out.println(soh.getBillToAddressID());
        System.out.println(soh.getShipToAdressID());
        System.out.println(soh.getShipMethodID());
        System.out.println(soh.getCreditCardID());
        System.out.println(soh.getCreditCardApprovalCode());
        System.out.println(soh.getCurrencyRateID());
        System.out.println(soh.getTaxAmt());
        System.out.println(soh.getFreight());
        System.out.println(soh.getComment());*/
        //Tratamos de ejecutar el callProcedure de la nueva venta para obtener el ID
        try {
            ordenID=sohImp.nuevaVenta(soh);
        } catch (Exception ex) {
            System.err.println(ex);
            System.err.println("No se pudo llevar a cabo la venta");
            return -1;
        }
        System.out.println("\nEl numero de orden es: "+Integer.toString(ordenID));
        //Ahora agregamos los detalles de la orden
        SalesOrderDetail sod=new SalesOrderDetail();
        DAOSalesOrderDetailImp sodImp=new DAOSalesOrderDetailImp();
        System.out.println("Agregando detalles a la orden:\n");
        sod.setSalesOrderID(ordenID);
        while("s".equals(agrMas)){
        //Pedimos los datos
            System.out.print("Ingrese el ID del producto: ");
            sod.setProductID(scn.nextInt());
            System.out.print("Ingrese la cantidad de unidades del producto: ");
            sod.setOrderQty(scn.nextShort());
            scn.nextLine();
            System.out.println("¿Agregar el numero de "
                + "seguimiento del transpotista?(Si->s/No->Otra tecla): ");
            if("s".equals(new Scanner(System.in).nextLine())){
                System.out.print("Ingrese el numero de seguimiento: ");
                sod.setCarrierTrackingNumber(scn.nextLine());
            }else
                sod.setCarrierTrackingNumber(null);
        //Ejecutamos el procedmiento almacenado a través del metodo detallesNuevaOrden
            try {sodImp.detallesOrdenNueva(sod);
            } catch (Exception ex) {
                System.err.println(ex);
            }
            System.out.println("¿Desea agregar más datos?(Si->s/No->Otra tecla): ");
            agrMas=scn.nextLine();
            }
        System.out.println("Orden "+Integer.toString(ordenID)+" terminada de registrar");
        return 0;
        }
    }
}
