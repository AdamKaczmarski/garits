package com.garits.report;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatReports extends CrudRepository<StatReport,Integer> {
    @Query(value="select count(*) from customers", nativeQuery = true)

    StatReport getSomething();
}
