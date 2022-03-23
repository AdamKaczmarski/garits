package com.garits.order;

import com.garits.exceptions.NotFound;
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
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{idOrder}")
    Order getOrderDetails(@PathVariable Integer idOrder){
        return orderRepository.findById(idOrder).orElseThrow(() -> new NotFound("Could not find order: "+idOrder));
    }

    /**
     * @ADEL
     */
    @DeleteMapping("/orders/{idOrder}")
    void deleteOrder(@PathVariable Integer idOrder){
        orderRepository.deleteById(idOrder);
    }
}
