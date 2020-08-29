import {Observable} from "rxjs";
import {CartService} from "./../cart.service";
import {CustomerService} from "./../customer.service";
import {OrderService} from "./../order.service";
import {Cart} from "./../cart";
import {Customer} from "./../customer";
import {Order} from "./../order";
import {OrderDetails} from "./../order_details";
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {CustomerComponent} from "../customer/customer.component";

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.css"]
})
export class CartComponent implements OnInit {
  carts: Observable<Cart[]>;
  total = 0;
  cartsArray = [];
  customerArray = [];
  orderArray = [];

  //downdrop menu
  customers: Observable<Customer[]>;

  countryForm: FormGroup;
  order: Order = new Order();
  cart: Cart;
  orderDetails: OrderDetails = new OrderDetails();

  constructor(private cartService: CartService, private customerService: CustomerService, private fb: FormBuilder, private orderService: OrderService, private route: ActivatedRoute,
              private router: Router, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.reloadData();

    this.cartService.getCartsList()
      .subscribe(data => {
        this.cartsArray = data;
      })

    this.customerService.getCustomerList()
      .subscribe(data => {
        this.customerArray = data;
      })

    this.countryForm = this.fb.group({
      countryControl: [this.customerArray[1]]
    });

    this.orderService.getOrderList()
      .subscribe(data => {
        this.orderArray = data;
      });
  }

  reloadData() {
    this.carts = this.cartService.getCartsList();
    this.customers = this.customerService.getCustomerList();
  }

  ngAfterContentChecked() {
    this.total = this.cartsArray.reduce(
      (prev, cur) => prev + cur.subtotal, 0);
  }

  deleteCarts(idd: number) {
    this.cartService.deleteCarts(idd)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
          this.redirectTo('/carts');
        },
        error => console.log(error));
  }


  redirectTo(uri: string) {
    this.router.navigateByUrl('/products', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }

  completeTheOrder() {
    this.order.customer = this.countryForm.controls['countryControl'].value;
    this.orderService.finalizeOrder(this.order)
      .subscribe(data =>
          console.log(data),
        error => console.log(error));
  }

  callall() {
    this.completeTheOrder();
    this.redirectTo('/products');
  }

  myfunction(){
    // console.log("Hello");
    // this.router.navigate(['/products']);
    const modalRef = this.modalService.open(CustomerComponent);
    modalRef.componentInstance.name = 'World';
  }

}
