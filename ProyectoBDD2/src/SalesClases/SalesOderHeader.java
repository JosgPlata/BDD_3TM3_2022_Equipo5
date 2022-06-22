package SalesClases;
import java.math.BigDecimal;
import java.sql.Date;

public class SalesOderHeader {
    private int salesOrderID;
    private short revisionNumber;
    private Date orderDate;
    private Date dueDate;
    private Date shipDate;
    private short status;
    private boolean onlineOrderFlag;
    private String salesOrderNumber;
    private String purchaseOrderNumber;
    private String accountNumber;
    private int customerID;
    private Integer salesPersonID;
    private Integer territoryID;
    private int billToAddressID;
    private int shipToAdressID;
    private int shipMethodID;
    private Integer creditCardID;
    private String creditCardApprovalCode;
    private Integer currencyRateID;
    private BigDecimal subTotal;
    private BigDecimal taxAmt;
    private BigDecimal freight;
    private BigDecimal totalDue;
    private String comment;
    private String rowguid;
    private Date modifiedDate;

    public SalesOderHeader() {
    }

    public SalesOderHeader(int salesOrderID, short revisionNumber,
            Date orderDate, Date dueDate, Date shipDate,
            short status, boolean onlineOrderFlag, String salesOrderNumber,
            String purchaseOrderNumber, String accountNumber, int customerID,
            Integer salesPersonID, Integer territoryID, int billToAddressID,
            int shipToAdressID, int shipMethodID, Integer creditCardID,
            String creditCardApprovalCode, Integer currencyRateID, BigDecimal subTotal,
            BigDecimal taxAmt, BigDecimal freight, BigDecimal totalDue, String comment,
            String rowguid, Date modifiedDate) {
        this.salesOrderID = salesOrderID;
        this.revisionNumber = revisionNumber;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
        this.shipDate = shipDate;
        this.status = status;
        this.onlineOrderFlag = onlineOrderFlag;
        this.salesOrderNumber = salesOrderNumber;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.accountNumber = accountNumber;
        this.customerID = customerID;
        this.salesPersonID = salesPersonID;
        this.territoryID = territoryID;
        this.billToAddressID = billToAddressID;
        this.shipToAdressID = shipToAdressID;
        this.shipMethodID = shipMethodID;
        this.creditCardID = creditCardID;
        this.creditCardApprovalCode = creditCardApprovalCode;
        this.currencyRateID = currencyRateID;
        this.subTotal = subTotal;
        this.taxAmt = taxAmt;
        this.freight = freight;
        this.totalDue = totalDue;
        this.comment = comment;
        this.rowguid = rowguid;
        this.modifiedDate = modifiedDate;
    }

    public int getSalesOrderID() {
        return salesOrderID;
    }

    public short getRevisionNumber() {
        return revisionNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public short getStatus() {
        return status;
    }
    
    public boolean getOnlineOrderFlag(){
        return onlineOrderFlag;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public Integer getSalesPersonID() {
        return salesPersonID;
    }

    public Integer getTerritoryID() {
        return territoryID;
    }

    public int getBillToAddressID() {
        return billToAddressID;
    }

    public int getShipToAdressID() {
        return shipToAdressID;
    }

    public int getShipMethodID() {
        return shipMethodID;
    }

    public Integer getCreditCardID() {
        return creditCardID;
    }

    public String getCreditCardApprovalCode() {
        return creditCardApprovalCode;
    }
    
    public Integer getCurrencyRateID() {
        return currencyRateID;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }

    public String getComment() {
        return comment;
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

    public void setRevisionNumber(short revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setOnlineOrderFlag(boolean onlineOrderFlag) {
        this.onlineOrderFlag = onlineOrderFlag;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setSalesPersonID(Integer salesPersonID) {
        this.salesPersonID = salesPersonID;
    }

    public void setTerritoryID(Integer territoryID) {
        this.territoryID = territoryID;
    }

    public void setBillToAddressID(int billToAddressID) {
        this.billToAddressID = billToAddressID;
    }

    public void setShipToAdressID(int shipToAdressID) {
        this.shipToAdressID = shipToAdressID;
    }

    public void setShipMethodID(int shipMethodID) {
        this.shipMethodID = shipMethodID;
    }

    public void setCreditCardID(Integer creditCardID) {
        this.creditCardID = creditCardID;
    }

    public void setCreditCardApprovalCode(String creditCardApprovalCode) {
        this.creditCardApprovalCode = creditCardApprovalCode;
    }

    public void setCurrencyRateID(Integer currencyRateID) {
        this.currencyRateID = currencyRateID;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    
   }
