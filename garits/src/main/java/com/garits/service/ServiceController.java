package com.garits.service;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    // GET MAPPINGS

    /**
     * This method returns all services in the system.
     *
     * @return Array of Service objects.
     */
    @GetMapping("/services")
    public @ResponseBody
    Iterable<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/services/short")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Iterable<Service> getShortInfoServices() {
        Iterable<Service> result = serviceRepository.findAll();
        Set<Service> services = new HashSet<>();
        for (Service s : result) {
            services.add(new Service(s.getIdService(), s.getServiceName(), s.getServicePrice()));
        }
        return services;
    }

    /**
     * Get single service
     *
     * @param id - service's id
     * @return Service object
     */
    @GetMapping("/services/{id}")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    Service one(@PathVariable Integer id) {
        return serviceRepository.findById(id).orElseThrow(() -> new NotFound("Could not find service: " + id));
    }

    /**
     * Get service's price. Just price, for the frontend to calculate total amount.
     * @param id - service's id
     * @return Service's price
     */
    @GetMapping("/services/{id}/price")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('MECHANIC') or hasRole('FOREPERSON') or hasRole('FRANCHISEE')")
    double getPrice(@PathVariable Integer id) {
        return serviceRepository.findById(id).orElseThrow(() -> new NotFound("Could not find the service: " + id)).getServicePrice();
    }

    /**
     * Add new service mapping.
     *
     * @param newService - service object
     * @return
     */
    @PostMapping("/services")
    @PreAuthorize("hasRole('FRANCHISEE')")
    Service newService(@RequestBody Service newService) {
        return serviceRepository.save(newService);
    }

    /**
     * Updates the service
     * @param idService - service ID
     * @param editedService edited Service object
     * @return Edited Service object
     */
    @PatchMapping("/services/{idService}")
    @PreAuthorize("hasRole('FRANCHISEE')")
    ResponseEntity<Service> updateService(@PathVariable Integer idService, @RequestBody Service editedService) {
        Service s = serviceRepository.findById(idService).orElseThrow(() -> new NotFound("Could not find the service: " + idService));
        if (editedService.getServiceName() != null) s.setServiceName(editedService.getServiceName());
        if (editedService.getServicePrice() >= 0.0) s.setServicePrice(editedService.getServicePrice());
        if (editedService.getApproxTimeMin() > 0) s.setApproxTimeMin(editedService.getApproxTimeMin());
        if (editedService.getShortDescription() != null) s.setShortDescription(editedService.getShortDescription());
        serviceRepository.save(s);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    //DELETE MAPPINGS

    /**
     * Deletes the service
     *
     * @param id - service ID
     */
    @DeleteMapping("/services/{id}")
    @PreAuthorize("hasRole('FRANCHISEE')")
    void deleteService(@PathVariable Integer id) {
        serviceRepository.deleteById(id);
    }
}





