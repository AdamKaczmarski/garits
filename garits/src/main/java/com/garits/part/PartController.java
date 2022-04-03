package com.garits.part;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "/")
public class PartController {
    @Autowired
    private PartRepository partRepository;
    //GET MAPPINGS

    /**
     * Returns all parts that are in the system.
     * @return Array of Part objects.
     */
    @GetMapping("/parts")
    Iterable<Part> allParts(){
        return partRepository.findAll();
    }
    @GetMapping("/parts/{partId}")
    Part singlePart(@PathVariable Integer partId){
        return partRepository.findById(partId).orElseThrow(()-> new NotFound("Could not find part: "+partId));
    }
    @GetMapping("/partNames")
    ResponseEntity<Iterable<Part>> getPartNames(){
    Iterable<Part> parts = partRepository.findAll();
    Set<Part> partNames = new HashSet<>();
    for (Part p: parts){
        Part p2 = new Part(p.getIdPart(),p.getPartName());
        partNames.add(p2);
    }
    return ResponseEntity.ok(partNames);
    }

    //POST MAPPINGS

    //PUT MAPPINGS

    //DELETE MAPPINGS
}
