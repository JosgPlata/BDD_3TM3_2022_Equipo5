package SalesClases;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
public class SalesOrderDetail {
    
    private int salesOrderID;
    private int salesOrderDetailID;
    private String carrierTrackingNumber; 
    private short orderQty;
    private int productID;
    private int specialOfferID;
    private BigDecimal unitPrice;
    private BigDecimal unitPriceDiscount;
    private BigDecimal lineTotal;
    private String rowguid;
    private Date modifiedDate;

    public SalesOrderDetail() {
    }
    
    public SalesOrderDetail(int salesOrderID, int salesOrderDetailID, 
        String carrierTrackingNumber, short orderQty, int productID, 
        int specialOfferID, BigDecimal unitPrice, BigDecimal unitPriceDiscount, 
        BigDecimal lineTotal, String rowguid, Date modifiedDate) {
        this.salesOrderID = salesOrderID;
        this.salesOrderDetailID = salesOrderDetailID;
        this.carrierTrackingNumber = carrierTrackingNumber;
        this.orderQty = orderQty;
        this.productID = productID;
        this.specialOfferID = specialOfferID;
        this.unitPrice = unitPrice;
        this.unitPriceDiscount = unitPriceDiscount;
        this.lineTotal = lineTotal;
        this.rowguid = rowguid;
        this.modifiedDate = modifiedDate;
    }
    
    public int getSalesOrderID() {
        return salesOrderID;
    }
    
    public int getSalesOrderDetailID() {
        return salesOrderDetailID;
    }

    public String getCarrierTrackingNumber() {
        return carrierTrackingNumber;
    }

    public short getOrderQty() {
        return orderQty;
    }

    public int getProductID() {
        return productID;
    }

    public int getSpecialOfferID() {
        return specialOfferID;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getUnitPriceDiscount() {
        return unitPriceDiscount;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public String getRowguid() {
        return rowguid;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setSalesOrderID(int salesOrderID) {
        this.salesOrderID = salesOrderID;
    }

    public void setSalesOrderDetailID(int salesOrderDetailID) {
        this.salesOrderDetailID = salesOrderDetailID;
    }

    public void setCarrierTrackingNumber(String carrierTrackingNumber) {
        this.carrierTrackingNumber = carrierTrackingNumber;
    }

    public void setOrderQty(short orderQty) {
        this.orderQty = orderQty;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setSpecialOfferID(int specialOfferID) {
        this.specialOfferID = specialOfferID;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitPriceDiscount(BigDecimal unitPriceDiscount) {
        this.unitPriceDiscount = unitPriceDiscount;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
}
