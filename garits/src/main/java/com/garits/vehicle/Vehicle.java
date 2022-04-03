package com.garits.vehicle;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    //FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle")
    private Integer idVehicle;
    @Column(name = "id_reg_no")
    private String idRegNo;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "model")
    private String model;
    @Column(name = "engine_serial_number")
    private String engineSerialNumber;
    @Column(name = "chassis_number")
    private String chassisNumber;
    @Column(name = "colour")
    private String colour;
    @Column(name = "last_mot")
    private Date lastMot;

    //GETTERS AND SETTERS
    public Integer getIdVehicle() {
        return idVehicle;
    }

    public String getIdRegNo() {
        return idRegNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineSerialNumber() {
        return engineSerialNumber;
    }

    public void setEngineSerialNumber(String engineSerialNumber) {
        this.engineSerialNumber = engineSerialNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Date getLastMot() {
        return lastMot;
    }

    public void setLastMot(Date lastMot) {
        this.lastMot = lastMot;
    }
}
