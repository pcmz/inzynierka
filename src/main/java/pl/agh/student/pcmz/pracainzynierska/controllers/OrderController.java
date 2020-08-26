package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.Order;
import pl.agh.student.pcmz.pracainzynierska.services.OrderService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrder() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId)
            throws ResourceNotFoundException {
        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderService.save(order);
    }

    @PostMapping("/orders/finalize")
    public Order finalizeOrder(@Valid @RequestBody Order order) throws IOException {
        return orderService.finalizeOrder(order);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Long orderId,
                                             @Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {
        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        order.setCustomer(orderDetails.getCustomer());

        final Order updatedOrder = orderService.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/orders/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Long orderId)
            throws ResourceNotFoundException {
        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        orderService.delete(order);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
