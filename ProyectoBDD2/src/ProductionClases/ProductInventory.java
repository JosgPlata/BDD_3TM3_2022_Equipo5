package ProductionClases;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInventory {
    private int locationID;
    private String name;
    private BigDecimal costRate;
    private BigDecimal availability;
    private Date modifiedDate;

    public ProductInventory() {
    }

    public ProductInventory(int locationID, String name, BigDecimal costRate, BigDecimal availability, Date modifiedDate) {
        this.locationID = locationID;
        this.name = name;
        this.costRate = costRate;
        this.availability = availability;
        this.modifiedDate = modifiedDate;
    }

    public BigDecimal getAvailability() {
        return availability;
    }

    public BigDecimal getCostRate() {
        return costRate;
    }

    public int getLocationID() {
        return locationID;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setAvailability(BigDecimal availability) {
        this.availability = availability;
    }

    public void setCostRate(BigDecimal costRate) {
        this.costRate = costRate;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
}
