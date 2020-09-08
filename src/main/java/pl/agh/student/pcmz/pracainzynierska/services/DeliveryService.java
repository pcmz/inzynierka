package pl.agh.student.pcmz.pracainzynierska.services;

import org.springframework.stereotype.Service;
import pl.agh.student.pcmz.pracainzynierska.exception.ResourceNotFoundException;
import pl.agh.student.pcmz.pracainzynierska.integrations.delivery.DeliveryIntegrationController;
import pl.agh.student.pcmz.pracainzynierska.integrations.delivery.mock.MockDeliveryController;
import pl.agh.student.pcmz.pracainzynierska.models.Delivery;
import pl.agh.student.pcmz.pracainzynierska.models.DeliveryAddress;
import pl.agh.student.pcmz.pracainzynierska.models.Order;
import pl.agh.student.pcmz.pracainzynierska.repositories.DeliveryAddressRepository;
import pl.agh.student.pcmz.pracainzynierska.repositories.DeliveryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    private final DeliveryIntegrationController deliveryIntegrationController = new MockDeliveryController();

    public DeliveryService(DeliveryRepository deliveryRepository, DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> findById(Long id) {
        return deliveryRepository.findById(id);
    }

    public Delivery save(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public void delete(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    public Delivery createDeliveryCombinedWithOrder(Order order) {
        Delivery delivery = new Delivery();
        delivery.setOrder(order);

//        DeliveryAddress latestDeliveryAddress = deliveryAddressRepository.findTopById();
        DeliveryAddress latestDeliveryAddress = deliveryAddressRepository.getOne(getTopId(deliveryAddressRepository.findAll()));

        delivery.setDeliveryAddress(latestDeliveryAddress);
        deliveryIntegrationController.createNewDelivery(delivery);
        return deliveryRepository.save(delivery);
    }

    public Delivery findByOrderId(Long orderId) throws ResourceNotFoundException {
        Delivery deliveryByOrderId = deliveryRepository.findByOrderId(orderId).orElseThrow(() -> new ResourceNotFoundException("Delivery not found for this order id :: " + orderId));
        ;
        deliveryIntegrationController.updateDeliveryState(deliveryByOrderId);
        deliveryRepository.save(deliveryByOrderId);
        return deliveryByOrderId;
    }

    private Long getTopId(List<DeliveryAddress> allDeliveryAddresses) {
        return allDeliveryAddresses.stream().map(DeliveryAddress::getId).max(Long::compare).get();
    }
}
