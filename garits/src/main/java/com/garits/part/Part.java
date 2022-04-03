package com.garits.part;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "parts")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_part")
    private Integer idPart;
    @Column(name="part_name")
    private String partName;
    @Column(name="part_type")
    private String partType;
    @Column(name="code")
    private String code;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name="vehicle_type")
    private String vehicleType;
    @Column(name="year_s")
    private String yearS;
    @Column(name="price")
    private double price;
    @Column(name="stock_level")
    private int stockLevel;
    @Column(name="stock_level_threshold")
    private int stockLevelThreshold;

    public Part(Integer idPart, String partName) {
        this.idPart = idPart;
        this.partName = partName;
    }

    public Part() {

    }

    public Part(Integer idPart, String partName, String partType, String code, String manufacturer, String vehicleType, String yearS, double price, int stockLevel, int stockLevelThreshold) {
        this.idPart = idPart;
        this.partName = partName;
        this.partType = partType;
        this.code = code;
        this.manufacturer = manufacturer;
        this.vehicleType = vehicleType;
        this.yearS = yearS;
        this.price = price;
        this.stockLevel = stockLevel;
        this.stockLevelThreshold = stockLevelThreshold;
    }

    public Integer getIdPart() {
        return idPart;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getYearS() {
        return yearS;
    }

    public void setYearS(String yearS) {
        this.yearS = yearS;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getStockLevelThreshold() {
        return stockLevelThreshold;
    }

    public void setStockLevelThreshold(int stockLevelThreshold) {
        this.stockLevelThreshold = stockLevelThreshold;
    }
}
