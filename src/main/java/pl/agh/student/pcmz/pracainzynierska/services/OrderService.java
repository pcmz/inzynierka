package pl.agh.student.pcmz.pracainzynierska.services;

import org.springframework.stereotype.Service;
import pl.agh.student.pcmz.pracainzynierska.models.Order;
import pl.agh.student.pcmz.pracainzynierska.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderDetailsService orderDetailsService;
    private final InvoiceService invoiceService;
    private final CartService cartService;
    private final DeliveryService deliveryService;

    public OrderService(OrderRepository orderRepository, OrderDetailsService orderDetailsService, InvoiceService invoiceService, CartService cartService, DeliveryService deliveryService) {
        this.orderRepository = orderRepository;
        this.orderDetailsService = orderDetailsService;
        this.invoiceService = invoiceService;
        this.cartService = cartService;
        this.deliveryService = deliveryService;
    }

    public Order finalizeOrder(Order order) {
        Order savedOrder = save(order);
        invoiceService.createProformaInvoice(savedOrder);
        orderDetailsService.createOrderDetails(savedOrder);
        deliveryService.createDeliveryCombinedWithOrder(savedOrder);
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
