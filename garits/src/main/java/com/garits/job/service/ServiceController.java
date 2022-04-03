package com.garits.job.service;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Get single service
     *
     * @param id - service's id
     * @return Service object
     */
    @GetMapping("/services/{id}")
    Service one(@PathVariable Integer id) {
        return serviceRepository.findById(id).orElseThrow(() -> new NotFound("Could not find service: " + id));
    }
    @GetMapping("/services/{id}/name")
    String getServiceName(@PathVariable Integer id){
        return serviceRepository.findById(id).orElseThrow(() -> new NotFound("Could not find service: " + id)).getServiceName();
    }
    //POST MAPPINGS

    /**
     * Add new service mapping.
     *
     * @param newService - service object
     * @return
     */
    @PostMapping("/services")
    Service newService(@RequestBody Service newService) {
        return serviceRepository.save(newService);
    }

    //PUT MAPPINGS

    /**
     * Edit service
     */
    @PutMapping("/services/{idService}")
    Service updateService(@PathVariable Integer idService, @RequestBody Service editedService) {
        Service s = serviceRepository.findById(idService).orElseThrow(() -> new NotFound("Could not find the service: " + idService));
        if (editedService.getServiceName() != null) s.setServiceName(editedService.getServiceName());
        if (editedService.getServicePrice() >= 0.0) s.setServicePrice(editedService.getServicePrice());
        if (editedService.getApproxTimeMin() > 0) s.setApproxTimeMin(editedService.getApproxTimeMin());
        if (editedService.getShortDescription() != null) s.setShortDescription(editedService.getShortDescription());
        return serviceRepository.save(s);
    }

    //DELETE MAPPINGS

    /**
     * Deletes the service
     *
     * @param id - service ID
     */
    @DeleteMapping("/services/{id}")
    void deleteService(@PathVariable Integer id) {
        serviceRepository.deleteById(id);
    }
}





