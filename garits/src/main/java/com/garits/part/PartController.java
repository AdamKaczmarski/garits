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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     *
     * @param partId
     * @return
     */
    @GetMapping("/parts/{partId}")
    Part singlePart(@PathVariable Integer partId){
        return partRepository.findById(partId).orElseThrow(()-> new NotFound("Could not find part: "+partId));
    }
    @GetMapping("/partNames")
    ResponseEntity<Iterable<Part>> getPartNames(){
    Iterable<Part> parts = partRepository.findAll();
    Set<Part> partNames = new HashSet<>();
    for (Part p: parts){
        Part p2 = new Part(p.getIdPart(),p.getPartName(),p.getStockLevel());
        partNames.add(p2);
    }
    return ResponseEntity.ok(partNames);
    }

    //POST MAPPINGS

    /**
     *
     * @param newPart
     * @return
     */
    @PostMapping("/parts")
    Part addPart(@RequestBody Part newPart){
        return partRepository.save(newPart);
    }
    //PATCH MAPPINGS

    /**
     *
     * @param partId
     * @param editedPart
     * @return
     */
    @PatchMapping("/parts/{partId}")
    Part updatePart(@PathVariable Integer partId,@RequestBody Part editedPart){
        return partRepository.findById(partId).map(p->{
            if(editedPart.getManufacturer()!=null)p.setManufacturer(editedPart.getManufacturer());
            if(editedPart.getCode()!=null)p.setCode(editedPart.getCode());
            if(editedPart.getPartName()!=null)p.setPartName(editedPart.getPartName());
            if(editedPart.getPartType()!=null)p.setPartType(editedPart.getPartType());
            if(editedPart.getPrice()!=p.getPrice())p.setPrice(editedPart.getPrice());
            if(editedPart.getStockLevel()!=p.getStockLevel())p.setStockLevel(editedPart.getStockLevel());
            if(editedPart.getStockLevelThreshold()!=p.getStockLevelThreshold())p.setStockLevelThreshold(editedPart.getStockLevelThreshold());
            if(editedPart.getVehicleType()!=null)p.setVehicleType(editedPart.getVehicleType());
            if(editedPart.getYearS()!=null)p.setYearS(editedPart.getYearS());
            return partRepository.save(p);
        }).orElseThrow(()->new NotFound("Could not find part: "+partId));
    }
    //DELETE MAPPINGS

    /**
     *
     * @param partId
     * @return
     */
    @DeleteMapping("/parts/{partId}")
    ResponseEntity<String> deletePart(@PathVariable Integer partId){
        partRepository.deleteById(partId);
        return ResponseEntity.status(HttpStatus.OK).body("Part deleted");
    }
}
