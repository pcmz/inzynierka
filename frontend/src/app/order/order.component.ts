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
  proformaInvoicesNames: Map<number, string>;
  vatInvoicesNames: Map<number, string>;
  proformaInvoicesAddresses: Map<number, string>;
  vatInvoicesAddresses: Map<number, string>;

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

  showDelivery(id: number) {
    this.router.navigate(['mock_delivery_website', id]);
  }

  openInNewTabProformaInvoice(order_id: number) {
    window.open(this.proformaInvoicesAddresses.get(order_id), "_blank");
  }

  createVatInvoice(order_id: number) {
    this.invoiceService.promoteProformaInvoiceIntoVat(order_id).subscribe((invoice: Invoice) => {
      this.invoiceService.getVatInvoiceAddresByOrderId(invoice.order.id).subscribe((newAddress: string) => {
        this.vatInvoicesAddresses.set(order_id, newAddress);
        this.redirectTo('orders');
      })
    });
  }

  createOrOpenVatInvoice(order_id: number) {
    const href = this.vatInvoicesAddresses.get(order_id);
    if (href != undefined) {
      window.open(href, "_blank");
    } else {
      this.createVatInvoice(order_id);
    }
  }

  populateInvoices() {
    this.proformaInvoicesNames = new Map();
    this.vatInvoicesNames = new Map();
    this.proformaInvoicesAddresses = new Map();
    this.vatInvoicesAddresses = new Map();
    console.log("Hello form populateInvoices")
    this.invoiceService.getAllInvoices()
      .subscribe((invoice: Invoice[]) => {
        console.log(invoice)
        this.invoices = invoice
        console.log(this.invoices)
        this.invoices.forEach(i => console.log(i));
        this.invoices.forEach((i: Invoice) => {
          this.proformaInvoicesNames.set(i.order.id, i.invoiceNameProforma)
          if (i.invoiceNameVat != null) {
            this.vatInvoicesNames.set(i.order.id, i.invoiceNameVat)
          } else {
            this.vatInvoicesNames.set(i.order.id, "create VAT")
          }
        });
        this.invoices.forEach((i: Invoice) => {
          this.invoiceService.getProformaInvoiceAddresByOrderId(i.order.id).subscribe((address: InvoiceAddress) => {
            this.proformaInvoicesAddresses.set(i.order.id, address.invoice_address)
          })
        });
        this.invoices.forEach((i: Invoice) => {
          this.invoiceService.getVatInvoiceAddresByOrderId(i.order.id).subscribe((address: InvoiceAddress) => {
            if (!address.invoice_address.includes("null")) {
              this.vatInvoicesAddresses.set(i.order.id, address.invoice_address)
            }
          })
        });
      })
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }
}
