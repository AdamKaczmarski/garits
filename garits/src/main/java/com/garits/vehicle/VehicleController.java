package com.garits.vehicle;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "/")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    //GET MAPPINGS

    /**
     * This functions returns an Iterable of objects of type Vehicle.
     * @return
     */
    @GetMapping("/vehicles")
    Iterable<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    /**
     * This function has been created for short info about a vehicle.
     * It is used for the front end to not obtain all the information about a vehicle. To preserve storage and fasten the process.
     * @param customerId The id of the Customer that vehicles are assigned to.
     * @return Iterable of short info about vehicles.
     */
    @GetMapping("/vehicles/{customerId}/short")
    @PreAuthorize("hasRole('MECHANIC') or hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Vehicle> getShortInfoVehicles(@PathVariable Integer customerId) {
        if (vehicleRepository.findCustomer(customerId) != null && vehicleRepository.findCustomer(customerId).equals("1")) {
            Iterable<Vehicle> vehicles = vehicleRepository.findAllCustomerVehicles(customerId);
            Set<Vehicle> result = new HashSet<>();
            for (Vehicle v : vehicles) {
                result.add(new Vehicle(v.getIdVehicle(), v.getIdRegNo()));
            }
            return result;
        }
        return null;
    }

    /**
     * @param customerId The id of the Customer that vehicles are assigned to.
     * @return Iterable of all vehicles that belong to the specified customer.
     */
    @GetMapping("/vehicles/{customerId}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Vehicle> getAllCustomerVehicles(@PathVariable Integer customerId) {
        if (vehicleRepository.findCustomer(customerId) != null && vehicleRepository.findCustomer(customerId).equals("1")) {
            return vehicleRepository.findAllCustomerVehicles(customerId);

        }
        return null;
    }

    //POST MAPPINGS

    /**
     * Create vehicle and assign it to the customer
     * @param customerId ID of the customer to assign the vehicle to
     * @param newVehicle Vehicle to be created
     */
    @PostMapping("/vehicles/{customerId}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    void addCustomerVehicle(@PathVariable Integer customerId, @RequestBody Vehicle newVehicle) {
        if (vehicleRepository.findCustomer(customerId) != null && vehicleRepository.findCustomer(customerId).equals("1")) {
            vehicleRepository.save(newVehicle);
            vehicleRepository.addVehicleToCustomer(customerId, newVehicle.getIdRegNo());
        } else {
            throw new NotFound("Could not find customer: " + customerId);
        }
    }

    //PATCH MAPPINGS

    /**
     * Updates the Vehicle object given that there's one in the database with the specified idRegNo
     *
     * @param idVehicle ID of the edited vehicle
     * @param editedVehicle Vehicle object with the updated information
     * @return
     */
    @PatchMapping("/vehicles/{idVehicle}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Vehicle updateVehicle(@PathVariable String idVehicle, @RequestBody Vehicle editedVehicle) {
        Vehicle v = vehicleRepository.findVehicle(idVehicle);
        if (v != null) {
            if (editedVehicle.getManufacturer() != null) v.setManufacturer(editedVehicle.getManufacturer());
            if (editedVehicle.getModel() != null) v.setModel(editedVehicle.getModel());
            if (editedVehicle.getEngineSerialNumber() != null)
                v.setEngineSerialNumber(editedVehicle.getEngineSerialNumber());
            if (editedVehicle.getChassisNumber() != null) v.setChassisNumber(editedVehicle.getChassisNumber());
            if (editedVehicle.getColour() != null) v.setColour(editedVehicle.getColour());
            if (editedVehicle.getLastMot() != null) v.setLastMot(editedVehicle.getLastMot());
            return vehicleRepository.save(v);
        } else {
            throw new NotFound("Could not find vehicle: " + editedVehicle.getIdRegNo());
        }
    }

    //DELETE MAPPINGS

    /**
     * Deletes the Vehicle object with the specified idRegNo and unassigns it from the customer
     * @param customerId ID of the customer to unassign the vehicle from
     * @param idRegNo ID of the vehicle to delete
     */
    @DeleteMapping("/vehicles/{customerId}/{idRegNo}")
    @PreAuthorize("hasRole('FRANCHISEE')")
    void deleteVehicle(@PathVariable("customerId") Integer customerId, @PathVariable("idRegNo") String idRegNo) {
        vehicleRepository.deleteCustomerVehicle(idRegNo, customerId);
        vehicleRepository.deleteVehicle(idRegNo);
    }

}
