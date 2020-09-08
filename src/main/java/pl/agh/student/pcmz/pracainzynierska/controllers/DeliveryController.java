package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.Delivery;
import pl.agh.student.pcmz.pracainzynierska.services.DeliveryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/delivery")
    public List<Delivery> getAllDeliveries() {
        return deliveryService.findAll();
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id)
            throws ResourceNotFoundException {
        Delivery delivery = deliveryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found for this id :: " + id));
        return ResponseEntity.ok().body(delivery);
    }

    @GetMapping("/delivery_by_order_id/{id}")
    public ResponseEntity<Delivery> getDeliveryByOrderId(@PathVariable Long id)
            throws ResourceNotFoundException {
        Delivery delivery = deliveryService.findByOrderId(id);
        return ResponseEntity.ok().body(delivery);
    }

    @PostMapping("/delivery")
    public Delivery createDelivery(@Valid @RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    @DeleteMapping("/delivery/{id}")
    public Map<String, Boolean> deleteDelivery(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Delivery delivery = deliveryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found for this id :: " + id));

        deliveryService.delete(delivery);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
