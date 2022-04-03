package com.garits.vehicle;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    //GET MAPPINGS

    /**
     * @param customerId
     * @return
     */
    // SECURE IT FOR NON EXISTENT CUSTOMERS. IF USER SEARCHES FOR CUSTOMER ID THAT DOESNT EXIST IT SHOULD RETURN ERROR
    @GetMapping("/vehicles/{customerId}")
    Iterable<Vehicle> getAllCustomerVehicles(@PathVariable Integer customerId) {
        if (vehicleRepository.findCustomer(customerId) != null && vehicleRepository.findCustomer(customerId).equals("1")) {
            Iterable<Vehicle> v= vehicleRepository.findAllCustomerVehicles(customerId);
            for (Vehicle vehicle : v) {
                System.out.println(vehicle.getLastMot());
            }
                    return v;   

        } else {
            return null;
        }
    }

    //POST MAPPINGS

    /**
     * @param customerId
     * @param newVehicle
     */
    @PostMapping("/vehicles/{customerId}")
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
     * @param idRegNo
     * @param editedVehicle
     * @return
     */
    @PatchMapping("/vehicles/{idRegNo}")
    Vehicle updateVehicle(@PathVariable String idRegNo, @RequestBody Vehicle editedVehicle) {
        Vehicle v = vehicleRepository.findVehicle(idRegNo);
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
            throw new NotFound("Could not find vehicle: "+idRegNo);
        }
    }

    //DELETE MAPPINGS

    /**
     * @param idRegNo
     */
    @DeleteMapping("/vehicles/{idRegNo}")
    void deleteVehicle( @PathVariable("idRegNo") String idRegNo) {
        vehicleRepository.deleteCustomerVehicle(idRegNo);
        vehicleRepository.deleteVehicle(idRegNo);
    }
}
