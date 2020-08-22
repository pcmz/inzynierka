import { Order } from "./order";
import { Product } from "./product";

export class OrderDetails {
    id: number;
    order: Order;
    product: Product;
    quantity: number;
    subtotal: number;
}
