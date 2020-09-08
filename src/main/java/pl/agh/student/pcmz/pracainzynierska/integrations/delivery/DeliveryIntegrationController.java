package pl.agh.student.pcmz.pracainzynierska.integrations.delivery;

import pl.agh.student.pcmz.pracainzynierska.models.Delivery;

public interface DeliveryIntegrationController {

    Delivery createNewDelivery(Delivery delivery);

    Delivery updateDeliveryState(Delivery delivery);
}
