import { Product } from "./product";

export class Cart {
    id: number;
    product: Product;
    quantity: number;
    subtotal: number;
    // active: boolean
}
