package pl.agh.student.pcmz.pracainzynierska.integrations.delivery.mock;

public enum MockDeliveryState {
    NEW("NEW"),
    RECEIVED_FROM_THE_SENDER("RECEIVED FROM THE SENDER"),
    ON_THE_WAY("ON THE WAY"),
    DELIVERED("DELIVERED");

    String niceName;

    MockDeliveryState(String niceName) {
        this.niceName = niceName;
    }
}
