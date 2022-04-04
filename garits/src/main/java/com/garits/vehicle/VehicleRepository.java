package com.garits.vehicle;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    @Query(value="SELECT 1 FROM customers_vehicles WHERE customer_id=:customerId LIMIT 1", nativeQuery = true)
    String findCustomer(@Param("customerId") Integer customerId);

    @Query(value = "SELECT * from vehicles v INNER JOIN customers_vehicles cv ON cv.reg_no_id = v.id_reg_no WHERE customer_id=:customerId", nativeQuery = true)
    Iterable<Vehicle> findAllCustomerVehicles(@Param("customerId") Integer customerId);

    @Query(value = "INSERT INTO customers_vehicles (customer_id,reg_no_id) VALUES (:customerId,:idRegNo)", nativeQuery = true)
    @Modifying
    @Transactional
    void addVehicleToCustomer(@Param("customerId") Integer customerId, @Param("idRegNo") String idRegNo);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM vehicles where id_reg_no=:idRegNo", nativeQuery = true)
    void deleteVehicle(@Param("idRegNo") String idRegNo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customers_vehicles where reg_no_id=:idRegNo AND customer_id=:customerId", nativeQuery = true)
    void deleteCustomerVehicle(@Param("idRegNo") String idRegNo, @Param("customerId") Integer customerId);

    @Query(value = "SELECT * FROM vehicles where id_vehicle=:idVehicle", nativeQuery = true)
    Vehicle findVehicle(@Param("idVehicle") String idVehicle);
}



