package com.garits.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    @GetMapping("/vehicles/{customerId}")
    Iterable<Vehicle> getAllCustomerVehicles(@PathVariable Integer customerId) {
        return vehicleRepository.findAllCustomerVehicles(customerId);
    }

    //POST MAPPINGS

    /**
     * @param customerId
     * @param newVehicle
     */
    @PostMapping("/vehicles/{customerId}")
    void addCustomerVehicle(@PathVariable Integer customerId, @RequestBody Vehicle newVehicle) {
        vehicleRepository.save(newVehicle);
        vehicleRepository.addVehicleToCustomer(customerId, newVehicle.getIdRegNo());
    }

    //PATCH MAPPINGS

    /**
     * Updates the Vehicle object given that there's one in the database with the specified idRegNo
     * @param idRegNo
     * @param editedVehicle
     * @return
     */
    @PatchMapping("/vehicles/{idRegNo}")
    Vehicle updateVehicle(@PathVariable String idRegNo, @RequestBody Vehicle editedVehicle) {
        Vehicle v = vehicleRepository.findVehicle(idRegNo);
        if (editedVehicle.getManufacturer() != null) v.setManufacturer(editedVehicle.getManufacturer());
        if (editedVehicle.getModel() != null) v.setModel(editedVehicle.getModel());
        if (editedVehicle.getEngineSerialNumber() != null)
            v.setEngineSerialNumber(editedVehicle.getEngineSerialNumber());
        if (editedVehicle.getChassisNumber() != null) v.setChassisNumber(editedVehicle.getChassisNumber());
        if (editedVehicle.getColour() != null) v.setColour(editedVehicle.getColour());
        if (editedVehicle.getLastMot() != null) v.setLastMot(editedVehicle.getLastMot());
        return vehicleRepository.save(v);

    }

    //DELETE MAPPINGS

    /**
     * @param customerId
     * @param idRegNo
     */
    @DeleteMapping("vehicles/{customerId}/{idRegNo}")
    void deleteVehicle(@PathVariable("customerId") Integer customerId, @PathVariable("idRegNo") String idRegNo) {
        vehicleRepository.deleteCustomerVehicle(idRegNo, customerId);
        vehicleRepository.deleteVehicle(idRegNo);
    }
}
