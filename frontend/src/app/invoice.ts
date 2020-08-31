import {Order} from "./order";

export class Invoice {
  id: number;
  order: Order;
  fakturaXlIdProforma: string;
  invoiceNameProforma: string;
  fakturaXlIdVat: string;
  invoiceNameVat: string;
}
