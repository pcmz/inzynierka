package pl.agh.student.pcmz.pracainzynierska.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import pl.agh.student.pcmz.pracainzynierska.models.Order;
import pl.agh.student.pcmz.pracainzynierska.repositories.OrderRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Log
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderDetailsService orderDetailsService;
    private final InvoiceService invoiceService;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, OrderDetailsService orderDetailsService, InvoiceService invoiceService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.orderDetailsService = orderDetailsService;
        this.invoiceService = invoiceService;
        this.cartService = cartService;
    }

    public Order finalizeOrder(Order order) throws IOException {
        log.warning("OrderService#finalizeOrder()");
        Order savedOrder = save(order);
        invoiceService.createInvoice(order);
        orderDetailsService.createOrderDetails(order);
        cartService.deleteAll();
        return savedOrder;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
