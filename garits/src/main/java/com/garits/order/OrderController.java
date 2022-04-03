package com.garits.order;

import com.garits.exceptions.NotFound;
import com.garits.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class OrderController {
    private OrderRepository orderRepository;

}
