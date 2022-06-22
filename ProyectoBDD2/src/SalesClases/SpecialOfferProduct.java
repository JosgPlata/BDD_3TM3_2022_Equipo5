package SalesClases;

import java.sql.Date;

public class SpecialOfferProduct {
    private int specialOfferID;
    private int productID;
    private String rowguid;
    private Date modifiedDate;

    public SpecialOfferProduct() {
    }

    public SpecialOfferProduct(int specialOfferID, int productID, String rowguid, Date modifiedDate) {
        this.specialOfferID = specialOfferID;
        this.productID = productID;
        this.rowguid = rowguid;
        this.modifiedDate = modifiedDate;
    }

    public int getSpecialOfferID() {
        return specialOfferID;
    }

    public int getProductID() {
        return productID;
    }

    public String getRowguid() {
        return rowguid;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setSpecialOfferID(int specialOfferID) {
        this.specialOfferID = specialOfferID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
}
