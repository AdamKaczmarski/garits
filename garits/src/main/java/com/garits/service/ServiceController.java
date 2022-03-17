package com.garits.service;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Iterable<Servicve> getAllServices() {
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
            return serviceRepository.findById(id).orElseThrow(() -> new NotFound("Could not find service: "+id));
        }
        //POST MAPPINGS

        /**
         * Add new service mapping.
         *
         * @param newService - service object
         * @return
         */
        @PostMapping("/service")
        Service newService(@RequestBody Service newService) {
            return serviceRepository.save(newService);
        }

        //PUT MAPPINGS

        /**
         * Change service's role
         *
         * @return
         */
        @PutMapping("/service/{id}/role")
        Service changeRole(@RequestBody Role newRole, @PathVariable Integer id) {
            return serviceRepository.findById(id).map(service -> {
                service.changeRole(newRole);
                return serviceRepository.save(service);
            }).orElseThrow(() -> new NotFound("Could not find service: "+id));
        }

        /**
         * EDIT SERVICE
         *
         * @param id - Edited services's id
         */
        @PutMapping("/service/{id}")
        Service editService(@RequestBody Service editedService, @PathVariable Integer id) {
            return serviceRepository.findById(id).map(service -> {
                service.setServiceName(editedService.getServiceName());
                service.setServicePrice(editedService.getServicePrice());
                service.setServicePrice(editedService.getApproxTimeMin());
                return serviceRepository.save(service);
            }).orElseThrow(() -> new NotFound("Could not find service: "+id));
        }
        //DELETE MAPPINGS

        /**
         * Deletes the service
         *
         * @param id - service ID
         */
        @DeleteMapping("/service/{id}")
        void deleteService(@PathVariable Integer id) {
            serviceRepository.deleteById(id);
        }
    }
}



