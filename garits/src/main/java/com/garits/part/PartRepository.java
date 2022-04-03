package com.garits.part;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PartRepository extends CrudRepository<Part,Integer> {
    @Query(value = "select id_part, part_name from parts",nativeQuery = true)
    Iterable<Part> allPartNames();
}
