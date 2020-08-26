import {Observable} from "rxjs";
import {OrderService} from "./../order.service";
import {OrderDetailsService} from "./../order_details.service";
import {Order} from "./../order";
import {Component, OnInit} from "@angular/core";
import {Router} from '@angular/router';
import {OrderDetails} from "./../order_details";
import {CartService} from "./../cart.service";
import {Invoice} from "../invoice";
import {InvoiceService} from "../invoice.service";
import {InvoiceAddress} from "../invoice_address";

@Component({
  selector: "app-order",
  templateUrl: "./order.component.html",
  styleUrls: ["./order.component.css"]
})
export class OrderComponent implements OnInit {
  orders: Observable<Order[]>;
  cartsArray = [];
  invoices = [];
  invoicesNames: Map<number, string>;
  invoicesAddresses: Map<number, string>;

  constructor(private orderService: OrderService,
              private router: Router, private orderDetailsService: OrderDetailsService, private cartService: CartService, private invoiceService: InvoiceService) {
  }

  ngOnInit() {
    this.reloadData();
    this.populateInvoices();

    this.cartService.getCartsList()
      .subscribe(data => {
        this.cartsArray = data;
      })
  }

  reloadData() {
    this.orders = this.orderService.getOrderList();
  }

  deleteOrder(id: number) {
    this.orderService.deleteOrder(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  order: Order;
  orderDetails: OrderDetails;

  showDetails(id: number) {
    this.router.navigate(['order_details', id]);
  }

  downloadInvoice(order_id: number) {
    var key = order_id;
    console.log("key = " + key)
    var href = this.invoicesAddresses.get(key);
    console.log("href = " + href)
    window.location.href = href;
    this.router.navigate(['orders'])
  }

  populateInvoices() {
    this.invoicesNames = new Map();
    this.invoicesAddresses = new Map();
    console.log("Hello form populateInvoices")
    this.invoiceService.getAllInvoice()
      .subscribe((invoice: Invoice[]) => {
        console.log(invoice)
        this.invoices = invoice
        console.log(this.invoices)
        this.invoices.forEach(i => console.log(i));
        this.invoices.forEach((i: Invoice) => this.invoicesNames.set(i.order.id, i.invoiceName));
        this.invoices.forEach((i: Invoice) => this.invoiceService.getInvoiceAddresByOrderId(i.order.id).subscribe((address: InvoiceAddress) => this.invoicesAddresses.set(i.order.id, address.invoice_address)));
        this.invoicesNames.forEach((value, key, map) => console.log(value));
        this.invoicesAddresses.forEach((value, key, map) => console.log(value));
      })
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }
}
