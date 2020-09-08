package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.DeliveryAddress;
import pl.agh.student.pcmz.pracainzynierska.repositories.DeliveryAddressRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class DeliveryAddressController {

    private final DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressController(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    @GetMapping("/delivery_addresses")
    public List<DeliveryAddress> getAllAddresses() {
        return deliveryAddressRepository.findAll();
    }

    @GetMapping("/delivery_addresses/{id}")
    public ResponseEntity<DeliveryAddress> getAddressById(@PathVariable(value = "id") Long addresstId)
            throws ResourceNotFoundException {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(addresstId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addresstId));
        return ResponseEntity.ok().body(deliveryAddress);
    }

    @PostMapping("/delivery_addresses")
    public DeliveryAddress createAddress(@Valid @RequestBody DeliveryAddress deliveryAddress) {
        return deliveryAddressRepository.save(deliveryAddress);
    }

    @PutMapping("/delivery_addresses/{id}")
    public ResponseEntity<DeliveryAddress> updateAddress(@PathVariable(value = "id") Long addresstId,
                                                         @Valid @RequestBody DeliveryAddress addressDetails) throws ResourceNotFoundException {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(addresstId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addresstId));

        deliveryAddress.setCity(addressDetails.getCity());
        final DeliveryAddress updatedAddress = deliveryAddressRepository.save(deliveryAddress);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/delivery_addresses/{id}")
    public Map<String, Boolean> deleteAddress(@PathVariable(value = "id") Long addressId)
            throws ResourceNotFoundException {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));

        deliveryAddressRepository.delete(deliveryAddress);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
