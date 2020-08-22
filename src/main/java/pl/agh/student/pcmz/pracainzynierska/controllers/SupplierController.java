package pl.agh.student.pcmz.pracainzynierska.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.Supplier;
import pl.agh.student.pcmz.pracainzynierska.repositories.SupplierRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable(value = "id") Long suppliertId)
            throws ResourceNotFoundException {
        Supplier supplier = supplierRepository.findById(suppliertId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + suppliertId));
        return ResponseEntity.ok().body(supplier);
    }
}
