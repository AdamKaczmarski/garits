package com.garits.job;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.garits.part.Part;
import com.garits.service.Service;
import com.garits.user.User;
import com.garits.vehicle.Vehicle;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "jobs")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Job {
    //FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Integer idJob;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @Column(name="status")
    private String status;
    @Column(name="description_done")
    private String descriptionDone;
    @Column(name="description_required")
    private String descriptionRequired;
    @Column(name="est_time_min")
    private int estTimeMin;
    @Column(name="act_time_min")
    private Integer actTimeMin = 0;
    @Column(name="create_date")
    private Timestamp createDate;
    @Column(name="booking_date")
    private Date bookingDate;
    @Column(name="fix_date")
    private Date fixDate;
    @Column(name="bay")
    private String bay;
    @ManyToMany
    @JoinTable(name = "users_jobs",joinColumns=@JoinColumn(name="job_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
    private Set<User> user;
    @ManyToMany
    @JoinTable(name="jobs_services",joinColumns=@JoinColumn(name="job_id"),inverseJoinColumns=@JoinColumn(name="service_id"))
    private Set<Service> services;
    @ManyToMany
    @JoinTable(name="jobs_parts",joinColumns=@JoinColumn(name="job_id"),inverseJoinColumns=@JoinColumn(name="part_id"))
    public Set<Part> parts;
    //GETTERS AND SETTER

    public Integer getIdJob() {
        return idJob;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescriptionDone() {
        return descriptionDone;
    }

    public void setDescriptionDone(String descriptionDone) {
        this.descriptionDone = descriptionDone;
    }

    public String getDescriptionRequired() {
        return descriptionRequired;
    }

    public void setDescriptionRequired(String descriptionRequired) {
        this.descriptionRequired = descriptionRequired;
    }

    public int getEstTimeMin() {
        return estTimeMin;
    }

    public void setEstTimeMin(int estTimeMin) {
        this.estTimeMin = estTimeMin;
    }

    public Integer getActTimeMin() {
        return actTimeMin;
    }

    public void setActTimeMin(Integer actTimeMin) {
        this.actTimeMin = actTimeMin;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getFixDate() {
        return fixDate;
    }

    public void setFixDate(Date fixDate) {
        this.fixDate = fixDate;
    }

    public String getBay() {
        return bay;
    }

    public void setBay(String bay) {
        this.bay = bay;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
