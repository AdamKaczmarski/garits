package com.garits;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="users")
public class User {
    /**
     * NEED TO ADD ALL FIELDS FROM THE TABLE
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID_user")
    private Integer ID_user;
    @Column(name="first_name")
    private String firstName;
    @Column(name="email")
    private String email;

    public Integer getID_user() {
        return ID_user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


