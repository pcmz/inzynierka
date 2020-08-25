package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.OrderDetails;
import pl.agh.student.pcmz.pracainzynierska.models.Product;
import pl.agh.student.pcmz.pracainzynierska.repositories.OrderDetailsRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @GetMapping("/order_details")
    public List<OrderDetails> getOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    @GetMapping("/order_details/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable(value = "id") Long orderDetailsId)
            throws ResourceNotFoundException {
        OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found for this id :: " + orderDetailsId));
        return ResponseEntity.ok().body(orderDetails);
    }

    @PostMapping("/order_details")
    public OrderDetails createOrderDetails(@Valid @RequestBody OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    @PutMapping("/order_details/{id}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable(value = "id") Long orderDetailsId,
                                                 @Valid @RequestBody OrderDetails orderDetailsDetails) throws ResourceNotFoundException {
        OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found for this id :: " + orderDetailsId));

        orderDetails.setQuantity(orderDetailsDetails.getQuantity());
        orderDetails.setSubtotal(orderDetailsDetails.getSubtotal());
        final OrderDetails updatedProduct = orderDetailsRepository.save(orderDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/order_details/{id}")
    public Map<String, Boolean> deleteOrderDetails(@PathVariable(value = "id") Long orderDetailsId)
            throws ResourceNotFoundException {
        OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found for this id :: " + orderDetailsId));

        orderDetailsRepository.delete(orderDetails);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
