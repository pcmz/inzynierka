import {Order} from "./order";
import {DeliveryAddress} from "./delivery_address";

export class Delivery {
  id: number;
  order: Order;
  deliveryAddress: DeliveryAddress;
  externalID: string;
  state: string;
}
