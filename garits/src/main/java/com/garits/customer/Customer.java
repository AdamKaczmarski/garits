package com.garits.customer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.garits.customer.discounts.FlexDiscount;
import com.garits.customer.discounts.VariableDiscount;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
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
    @Column(name="fixed_discount", nullable = true)
    private Integer fixedDiscount;
    @Column(name="is_account_holder")
    private boolean isAccountHolder;
    @OneToMany(mappedBy = "customerId")
    private Set<FlexDiscount> flexDiscounts = new HashSet<>();

    @OneToMany(mappedBy = "customerId")
    private Set<VariableDiscount> variableDiscounts = new HashSet<>();

    public Customer() {
    }

    public Customer(Integer idCustomer, String name) {
        this.idCustomer = idCustomer;
        this.name = name;
    }

    public Customer(Integer idCustomer, String name, boolean isAccountHolder) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.isAccountHolder = isAccountHolder;
    }

    public Customer(Integer idCustomer, String name, String city, String address, String postcode, String telephoneNumber, String email, String fax, Integer fixedDiscount, boolean isAccountHolder, Set<FlexDiscount> flexDiscounts, Set<VariableDiscount> variableDiscounts) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.city = city;
        this.address = address;
        this.postcode = postcode;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.fax = fax;
        this.fixedDiscount = fixedDiscount;
        this.isAccountHolder = isAccountHolder;
        this.flexDiscounts = flexDiscounts;
        this.variableDiscounts = variableDiscounts;
    }

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

    public Integer getFixedDiscount() {
        return fixedDiscount;
    }

    public void setFixedDiscount(Integer fixedDiscount) {
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

    public boolean isAccountHolder() {
        return isAccountHolder;
    }

    public void setAccountHolder(boolean accountHolder) {
        isAccountHolder = accountHolder;
    }
}
