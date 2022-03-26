package com.garits.job.service;

import javax.persistence.*;

@Entity
@Table(name = "services")
//Fields
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Integer idService;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "service_price")
    private double servicePrice;
    @Column(name = "approx_time_min")
    private int approxTimeMin;
    @Column(name="short_description")
    private String shortDescription;
    //Getters and Setters
    public Integer getIdService() {
        return idService;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public double getServicePrice() {
        return servicePrice;
    }
    
    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
    
    public int getApproxTimeMin() {
        return approxTimeMin;
    }
    
    public void setApproxTimeMin(int approxTimeMin) {
        this.approxTimeMin = approxTimeMin;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
