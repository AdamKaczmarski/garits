package com.garits.customer;


import com.garits.customer.discounts.FlexDiscount;
import com.garits.customer.discounts.VariableDiscount;
import com.garits.exceptions.NotFound;
import com.garits.service.Service;
import com.garits.service.ServiceRepository;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Integer idCustomer;
    @Column(name="name")
    private String name;
    @Column(name="city")
    private String city;
    @Column(name="address")
    private String address;
    @Column(name="postcode")
    private String postcode;
    @Column(name="telephone_number")
    private String telephoneNumber;
    @Column(name="email")
    private String email;
    @Column(name="fax")
    private String fax;
    @Column(name="fixed_discount")
    private int fixedDiscount;
    @OneToMany(mappedBy = "customerId")
    private Set<FlexDiscount> flexDiscounts = new HashSet<>();

    @OneToMany(mappedBy = "customerId")
    private Set<VariableDiscount> variableDiscounts = new HashSet<>();


    public Integer getIdCustomer() {
        return idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public int getFixedDiscount() {
        return fixedDiscount;
    }

    public void setFixedDiscount(int fixedDiscount) {
        this.fixedDiscount = fixedDiscount;
    }

    public Set<FlexDiscount> getFlexDiscounts() {
        return flexDiscounts;
    }

    public void setFlexDiscounts(Set<FlexDiscount> flexDiscounts) {
        this.flexDiscounts = flexDiscounts;
    }

    public Set<VariableDiscount> getVariableDiscounts() {
        return variableDiscounts;
    }

    public void setVariableDiscounts(Set<VariableDiscount> variableDiscounts) {
        this.variableDiscounts = variableDiscounts;
    }

}
