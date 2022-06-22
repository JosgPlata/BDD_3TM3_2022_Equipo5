package SalesClases;

import java.sql.Date;

public class Customer {
    
    private int customerID;
    private Integer personID;
    private Integer storeID;
    private Integer territoryID;
    private String accountNumber;
    private String rowguid;
    private Date ModifiedDate;

    public Customer() {
    }

    public Customer(int customerID, Integer personID, Integer storeID, Integer territoryID, String accountNumber, String rowguid, Date ModifiedDate) {
        this.customerID = customerID;
        this.personID = personID;
        this.storeID = storeID;
        this.territoryID = territoryID;
        this.accountNumber = accountNumber;
        this.rowguid = rowguid;
        this.ModifiedDate = ModifiedDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public Integer getStoreID() {
        return storeID;
    }

    public Integer getTerritoryID() {
        return territoryID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getRowguid() {
        return rowguid;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public void setStoreID(Integer storeID) {
        this.storeID = storeID;
    }

    public void setTerritoryID(Integer territoryID) {
        this.territoryID = territoryID;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public void setModifiedDate(Date ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }    
    
}
