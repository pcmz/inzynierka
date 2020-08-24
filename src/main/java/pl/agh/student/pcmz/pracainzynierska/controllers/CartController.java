package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.models.Cart;
import pl.agh.student.pcmz.pracainzynierska.services.CartService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public List<Cart> getAllCarts() {
        return cartService.findAll();
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCartsById(@PathVariable(value = "id") Long cartsId)
            throws ResourceNotFoundException {
        Cart cart = cartService.findById(cartsId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id :: " + cartsId));
        return ResponseEntity.ok().body(cart);
    }

    @PostMapping("/carts")
    public Cart createCarts(@Valid @RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> updateCarts(@PathVariable(value = "id") Long cartsId,
                                            @Valid @RequestBody Cart cartsDetails) throws ResourceNotFoundException {
        Cart cart = cartService.findById(cartsId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id :: " + cartsId));

        cart.setQuantity(cartsDetails.getQuantity());
        final Cart updatedCart = cartService.save(cart);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/carts/{id}")
    public Map<String, Boolean> deleteCarts(@PathVariable(value = "id") Long cartsId)
            throws ResourceNotFoundException {
        Cart cart = cartService.findById(cartsId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found for this id :: " + cartsId));

        cartService.delete(cart);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/carts/removeAll")
    public Map<String, Boolean> removeAll() {
        cartService.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
