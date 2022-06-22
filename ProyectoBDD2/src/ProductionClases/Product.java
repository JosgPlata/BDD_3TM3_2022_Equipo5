package ProductionClases;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int productID;
    private String name;
    private String productNumber;
    private boolean makeFlag;
    private boolean finishedGoodsFlag;
    private String color;
    private short afetyStockLevel;
    private short reorderPoing;
    private BigDecimal standarCost;
    private BigDecimal listPrice;
    private String size;
    private String SizeUniteMeasureCode;
    private String WeightUniteMeasureCode;
    private String Weight;
    private int DaysToManufacture;
    private String ProductLine;
    private String Clase;
    private String Style;
    private int ProductSubCategoryID;
    private int ProductModelID;
    private Date SellStartDate;
    private Date SellEndDate;
    private Date DiscontinuedDate;
    private String rowguid;
    private Date ModifiedDate;

    public Product() {
    }

    public Product(int productID, String name, String productNumber, 
            boolean makeFlag, boolean finishedGoodsFlag, String color, 
            short afetyStockLevel, short reorderPoing, BigDecimal standarCost, 
            BigDecimal listPrice, String size, String SizeUniteMeasureCode, 
            String WeightUniteMeasureCode, String Weight, int DaysToManufacture, 
            String ProductLine, String Clase, String Style, int ProductSubCategoryID, 
            int ProductModelID, Date SellStartDate, Date SellEndDate, 
            Date DiscontinuedDate, String rowguid, Date ModifiedDate) {
        this.productID = productID;
        this.name = name;
        this.productNumber = productNumber;
        this.makeFlag = makeFlag;
        this.finishedGoodsFlag = finishedGoodsFlag;
        this.color = color;
        this.afetyStockLevel = afetyStockLevel;
        this.reorderPoing = reorderPoing;
        this.standarCost = standarCost;
        this.listPrice = listPrice;
        this.size = size;
        this.SizeUniteMeasureCode = SizeUniteMeasureCode;
        this.WeightUniteMeasureCode = WeightUniteMeasureCode;
        this.Weight = Weight;
        this.DaysToManufacture = DaysToManufacture;
        this.ProductLine = ProductLine;
        this.Clase = Clase;
        this.Style = Style;
        this.ProductSubCategoryID = ProductSubCategoryID;
        this.ProductModelID = ProductModelID;
        this.SellStartDate = SellStartDate;
        this.SellEndDate = SellEndDate;
        this.DiscontinuedDate = DiscontinuedDate;
        this.rowguid = rowguid;
        this.ModifiedDate = ModifiedDate;
    }

    public short getAfetyStockLevel() {
        return afetyStockLevel;
    }

    public String getClase() {
        return Clase;
    }

    public String getColor() {
        return color;
    }

    public int getDaysToManufacture() {
        return DaysToManufacture;
    }

    public Date getDiscontinuedDate() {
        return DiscontinuedDate;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }

    public String getName() {
        return name;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductLine() {
        return ProductLine;
    }

    public int getProductModelID() {
        return ProductModelID;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public int getProductSubCategoryID() {
        return ProductSubCategoryID;
    }

    public short getReorderPoing() {
        return reorderPoing;
    }

    public String getRowguid() {
        return rowguid;
    }

    public Date getSellEndDate() {
        return SellEndDate;
    }

    public Date getSellStartDate() {
        return SellStartDate;
    }

    public String getSize() {
        return size;
    }

    public String getSizeUniteMeasureCode() {
        return SizeUniteMeasureCode;
    }

    public BigDecimal getStandarCost() {
        return standarCost;
    }

    public String getStyle() {
        return Style;
    }

    public String getWeight() {
        return Weight;
    }

    public String getWeightUniteMeasureCode() {
        return WeightUniteMeasureCode;
    }

    public void setAfetyStockLevel(short afetyStockLevel) {
        this.afetyStockLevel = afetyStockLevel;
    }

    public void setClase(String Clase) {
        this.Clase = Clase;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDaysToManufacture(int DaysToManufacture) {
        this.DaysToManufacture = DaysToManufacture;
    }

    public void setFinishedGoodsFlag(boolean finishedGoodsFlag) {
        this.finishedGoodsFlag = finishedGoodsFlag;
    }

    public void setDiscontinuedDate(Date DiscontinuedDate) {
        this.DiscontinuedDate = DiscontinuedDate;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public void setMakeFlag(boolean makeFlag) {
        this.makeFlag = makeFlag;
    }

    public void setModifiedDate(Date ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductLine(String ProductLine) {
        this.ProductLine = ProductLine;
    }

    public void setProductModelID(int ProductModelID) {
        this.ProductModelID = ProductModelID;
    }

    public void setProductSubCategoryID(int ProductSubCategoryID) {
        this.ProductSubCategoryID = ProductSubCategoryID;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public void setReorderPoing(short reorderPoing) {
        this.reorderPoing = reorderPoing;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public void setSellEndDate(Date SellEndDate) {
        this.SellEndDate = SellEndDate;
    }

    public void setSellStartDate(Date SellStartDate) {
        this.SellStartDate = SellStartDate;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setStandarCost(BigDecimal standarCost) {
        this.standarCost = standarCost;
    }

    public void setStyle(String Style) {
        this.Style = Style;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public void setWeightUniteMeasureCode(String WeightUniteMeasureCode) {
        this.WeightUniteMeasureCode = WeightUniteMeasureCode;
    }

    public void setSizeUniteMeasureCode(String SizeUniteMeasureCode) {
        this.SizeUniteMeasureCode = SizeUniteMeasureCode;
    }
     
}
