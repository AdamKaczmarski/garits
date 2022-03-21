package com.garits.part;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //POST MAPPINGS

    //PUT MAPPINGS

    //DELETE MAPPINGS
}
