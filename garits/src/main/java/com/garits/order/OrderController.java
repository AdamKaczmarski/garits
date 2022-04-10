package com.garits.order;

import com.garits.exceptions.NotFound;
import com.garits.part.Part;
import com.garits.part.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PartRepository partRepository;
    /**
     * ADEL
     *
     * @return
     */
    @GetMapping("/orders")
    Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
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
    /**
     * Adam
     * Add items to order
     *
     */
     @PostMapping("/orders/{idOrder}/items")
     ResponseEntity<String> addItemsToOrder(@PathVariable Integer idOrder, @RequestBody List<PartOrder> pos){
         for (PartOrder x: pos){
             System.out.println("#########");
             System.out.println(x.getPartId());
             System.out.println(idOrder);
             System.out.println(x.getQuantity());
            orderRepository.addPartToOrder(x.getPartId(),idOrder,x.getQuantity());
         }
         orderRepository.setOrderTotalAmount(idOrder);
         return ResponseEntity.ok("ok");
     }
    /**
     * Adam
     * @param idOrder
     * @param newStatus
     * @return
     */
    @PatchMapping(value="/orders/{idOrder}/status")
    ResponseEntity<String> changeOrderStatus(@PathVariable Integer idOrder, @RequestBody Status newStatus) {
        Order o = orderRepository.findById(idOrder).orElseThrow(() -> new NotFound("Could not find order: " + idOrder));
        if (newStatus!=null) {
            o.setStatus(newStatus.getStatus());
            orderRepository.save(o);
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
        if (editedOrder.getStatus().equals("completed")){
            Iterable<Part> parts = partRepository.findPartsByOrderId(idOrder);
            for (Part p: parts){
                partRepository.updatePartStockFromOrder(p.getIdPart(),idOrder);
            }
        }
        return orderRepository.save(o);
    }



    /**
     * @ADEL
     */
    @DeleteMapping("/orders/{idOrder}")
    void deleteOrder(@PathVariable Integer idOrder) {
        orderRepository.deletePartsOrders(idOrder);
        orderRepository.deleteById(idOrder);
    }
}
