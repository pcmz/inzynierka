package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.Address;
import pl.agh.student.pcmz.pracainzynierska.models.Customer;
import pl.agh.student.pcmz.pracainzynierska.repositories.AddressRepository;
import pl.agh.student.pcmz.pracainzynierska.repositories.CustomerRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AdressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/addresses")
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") Long addresstId)
            throws ResourceNotFoundException {
        Address address = addressRepository.findById(addresstId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addresstId));
        return ResponseEntity.ok().body(address);
    }

    @PostMapping("/addresses")
    public Address createAddress(@Valid @RequestBody Address address) {
        return addressRepository.save(address);
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Long addresstId,
                                                   @Valid @RequestBody Address addressDetails) throws ResourceNotFoundException {
        Address address = addressRepository.findById(addresstId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addresstId));

        address.setCity(addressDetails.getCity());
        final Address updatedAddress = addressRepository.save(address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/addresses/{id}")
    public Map<String, Boolean> deleteAddress(@PathVariable(value = "id") Long addressId)
            throws ResourceNotFoundException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));

        addressRepository.delete(address);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
