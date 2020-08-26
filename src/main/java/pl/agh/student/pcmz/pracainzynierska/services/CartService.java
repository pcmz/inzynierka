package pl.agh.student.pcmz.pracainzynierska.services;

import org.springframework.stereotype.Service;
import pl.agh.student.pcmz.pracainzynierska.models.Cart;
import pl.agh.student.pcmz.pracainzynierska.repositories.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Optional<Cart> findById(Long cartsId) {
        return cartRepository.findById(cartsId);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    public void deleteAll() {
        cartRepository.deleteAll();
    }
}
