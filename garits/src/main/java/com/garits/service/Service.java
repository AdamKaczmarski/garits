package com.garits.service;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "services")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
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

    public Service() {
    }

    public Service(Integer idService) {
        this.idService = idService;
    }

    public Service(Integer idService, String serviceName) {
        this.idService = idService;
        this.serviceName = serviceName;
    }

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
