package com.garits.user;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    //FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "role_name")
    private String roleName;
    @Column(name="hourly_rate")
    private int hourlyRate;
    // CONSTRUCTORS
    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(Integer idRole) {
        this.idRole = idRole;
    }

    public Role(Integer idRole, String roleName, int hourlyRate) {
        this.idRole = idRole;
        this.roleName = roleName;
        this.hourlyRate = hourlyRate;
    }

    public Role(Integer idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }


    //GETTERS AND SETTERS
    public Integer getIdRole() {
        return idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
