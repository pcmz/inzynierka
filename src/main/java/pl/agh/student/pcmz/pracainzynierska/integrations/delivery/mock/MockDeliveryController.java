package pl.agh.student.pcmz.pracainzynierska.integrations.delivery.mock;

import pl.agh.student.pcmz.pracainzynierska.integrations.delivery.DeliveryIntegrationController;
import pl.agh.student.pcmz.pracainzynierska.models.Delivery;

import java.util.Random;

public class MockDeliveryController implements DeliveryIntegrationController {

    private static final Random random = new Random();

    public Delivery createNewDelivery(Delivery delivery) {
        delivery.setExternalID(String.format("%.0f", random.nextDouble() * 1000));
        delivery.setState(MockDeliveryState.NEW.niceName);
        return delivery;
    }

    public Delivery updateDeliveryState(Delivery delivery) {
        delivery.setState(randNewDeliveryState());
        return delivery;
    }

    private String randNewDeliveryState() {
        MockDeliveryState[] values = MockDeliveryState.values();
        int randomInt = random.nextInt(values.length);
        return values[randomInt].niceName;
    }

}
