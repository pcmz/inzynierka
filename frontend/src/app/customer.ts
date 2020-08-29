import {Address} from "./address";

export class Customer {
  id: number;
  name: string;
  nip: string;
  address = {} as Address;
  customerEmail: string;
  customerPhone: string;
  // shippingAddress: string;
}
