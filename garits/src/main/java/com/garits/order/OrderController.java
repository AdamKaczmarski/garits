package com.garits.order;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class OrderController {
    private OrderRepository orderRepository;
    /**
     * ADEL
     * @return
     */
    @GetMapping("/orders")
    Iterable<Order> getAllOrders(){
        return null;
    }

    @GetMapping("/orders/{idOrder}")
    Iterable<OrderPart> getOrderDetails(@PathVariable Integer idOrder){
        return null;
    }


    /**
     * @ADEL
     */
    @DeleteMapping("/orders/{idOrder}")
    void deletOrder(@PathVariable Integer idOrder){

    }
}
