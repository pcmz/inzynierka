package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.OrderDetails;
import pl.agh.student.pcmz.pracainzynierska.services.OrderDetailsService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/order_details")
    public List<OrderDetails> getOrderDetails() {
        return orderDetailsService.findAll();
    }

    @GetMapping("/order_details/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable(value = "id") Long orderDetailsId)
            throws ResourceNotFoundException {
        OrderDetails orderDetails = orderDetailsService.findById(orderDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found for this id :: " + orderDetailsId));
        return ResponseEntity.ok().body(orderDetails);
    }

    @PostMapping("/order_details")
    public OrderDetails createOrderDetails(@Valid @RequestBody OrderDetails orderDetails) {
        return orderDetailsService.save(orderDetails);
    }

    @PutMapping("/order_details/{id}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable(value = "id") Long orderDetailsId,
                                                 @Valid @RequestBody OrderDetails orderDetailsDetails) throws ResourceNotFoundException {
        OrderDetails orderDetails = orderDetailsService.findById(orderDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found for this id :: " + orderDetailsId));

        orderDetails.setQuantity(orderDetailsDetails.getQuantity());
        orderDetails.setSubtotal(orderDetailsDetails.getSubtotal());
        final OrderDetails updatedProduct = orderDetailsService.save(orderDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/order_details/{id}")
    public Map<String, Boolean> deleteOrderDetails(@PathVariable(value = "id") Long orderDetailsId)
            throws ResourceNotFoundException {
        OrderDetails orderDetails = orderDetailsService.findById(orderDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found for this id :: " + orderDetailsId));

        orderDetailsService.delete(orderDetails);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
