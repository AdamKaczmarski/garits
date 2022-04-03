package com.garits.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PartsOrdersDetailController {
    @Autowired
    private PartsOrdersDetailRepository partsOrdersRepository;
    @GetMapping("/partsorders/{idOrder}")
    Iterable<PartsOrdersDetail> getAllByOrderId(){
        return partsOrdersRepository.findAll();
    }
}
