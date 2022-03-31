package com.garits.order;

import com.garits.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    /**
     * ADEL
     *
     * @return
     */
    @GetMapping("/orders")
    Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{idOrder}")
    Order getOrderDetails(@PathVariable Integer idOrder) {
        return orderRepository.findById(idOrder).orElseThrow(() -> new NotFound("Could not find order: " + idOrder));
    }

    /**
     * Adam
     *
     * @param newOrder
     * @return
     */
    @PostMapping("/orders")
    Order addOrder(@RequestBody Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @PatchMapping("/orders/{idOrder}/status")
    ResponseEntity<String> changeOrderStatus(@PathVariable Integer idOrder, @RequestBody String newStatus) {
        Order o = orderRepository.findById(idOrder).orElseThrow(() -> new NotFound("Could not find order: " + idOrder));
        if (newStatus!=null) {
            o.setStatus(newStatus);
            return ResponseEntity.status(HttpStatus.OK).body("Changed the status");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provided empty status");
    }

    @PatchMapping("/orders/{idOrder}")
    Order editOrder(@PathVariable Integer idOrder, @RequestBody Order editedOrder) {
        Order o = orderRepository.findById(idOrder).orElseThrow(() -> new NotFound("Could not find order: " + idOrder));
        if (editedOrder.getStatus() != null) o.setStatus(editedOrder.getStatus());
        if (editedOrder.getDescription() != null) o.setDescription(editedOrder.getDescription());
        if (editedOrder.getOrderDate() != null) o.setOrderDate(editedOrder.getOrderDate());
        if (editedOrder.getOrderAmount() > 0) o.setOrderAmount(editedOrder.getOrderAmount());
        if (editedOrder.getOrderArrival() != null) o.setOrderArrival(editedOrder.getOrderArrival());
        return orderRepository.save(o);
    }

    /**
     * @ADEL
     */
    @DeleteMapping("/orders/{idOrder}")
    void deleteOrder(@PathVariable Integer idOrder) {
        orderRepository.deleteById(idOrder);
    }
}
