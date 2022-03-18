package com.garits.service;

import com.garits.service.Service;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ServiceRepository extends CrudRepository<Service, Integer> {
    @Query(value = "SELECT * from services v INNER JOIN jobs_vehicles jv ON jv.service_id = v.id_reg_no WHERE service_id=:serviceId", nativeQuery = true)
    Iterable<Service> findAllServices(@Param("serviceId") Integer serviceId);

    @Query(value = "INSERT INTO jobs_services (customer_id,service_id) VALUES (:serviceId,:idService)", nativeQuery = true)
    @Modifying
    @Transactional
    void addService(@Param("jobId") Integer serviceId, @Param("idService") Integer idService);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM services where service_id=:idService", nativeQuery = true)
    void deleteService(@Param("idService") Integer idService);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM jobs_services where service_id=:idService AND service_id=:serviceId", nativeQuery = true)
    void deleteService(@Param("idService") Integer idService, @Param("serviceId") Integer serviceId);

    @Query(value = "SELECT * FROM services where idService=:idService", nativeQuery = true)
    Service findService(@Param("idService") Integer idService);
}
