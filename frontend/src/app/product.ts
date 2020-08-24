import {Supplier} from "./supplier";

export class Product {
    id: number;
    productName: string;
    unit: string;
    supplier: Supplier;
    quantityPerUnit: string;
    unitPrice: number;
    ipath: string;
    availableColours: string;
  quantity: number;
  vat: number;
    // active: boolean
}
