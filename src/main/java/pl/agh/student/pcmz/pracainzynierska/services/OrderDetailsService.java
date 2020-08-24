package pl.agh.student.pcmz.pracainzynierska.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import pl.agh.student.pcmz.pracainzynierska.models.Cart;
import pl.agh.student.pcmz.pracainzynierska.models.Order;
import pl.agh.student.pcmz.pracainzynierska.models.OrderDetails;
import pl.agh.student.pcmz.pracainzynierska.repositories.CartRepository;
import pl.agh.student.pcmz.pracainzynierska.repositories.OrderDetailsRepository;

import java.util.List;
import java.util.Optional;

@Log
@Service
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    private final CartRepository cartRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, CartRepository cartRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.cartRepository = cartRepository;
    }

    public void createOrderDetails(Order order) {
        log.warning("OrderDetailsService#createOrderDetails");
        List<Cart> carts = cartRepository.findAll();
        for (Cart cart : carts) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setProduct(cart.getProduct());
            orderDetails.setQuantity(cart.getQuantity());
            orderDetails.setSubtotal(cart.getSubtotal());
            save(orderDetails);
        }
    }

    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    public Optional<OrderDetails> findById(Long orderDetailsId) {
        return orderDetailsRepository.findById(orderDetailsId);
    }

    public void delete(OrderDetails orderDetails) {
        orderDetailsRepository.delete(orderDetails);
    }
}
