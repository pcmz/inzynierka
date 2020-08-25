import { Observable, empty, of  } from "rxjs";
import { CartService } from "./../cart.service";
import { CustomerService } from "./../customer.service";
import { OrderService } from "./../order.service";
import { OrderDetailsService } from "./../order_details.service";
import { Cart } from "./../cart";
import { Customer } from "./../customer";
import { Order } from "./../order";
import { OrderDetails } from "./../order_details";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { reduce, switchMap  } from 'rxjs/operators';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

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
  // customerArray: Customer[] = [];
  order: Order = new Order();
  // order2: Order = new Order();

  //order_details
  // id: number;
  cart: Cart;
  orderDetails: OrderDetails = new OrderDetails();

  constructor(private cartService: CartService, private customerService: CustomerService, private fb: FormBuilder, private orderService: OrderService, private route: ActivatedRoute,
    private router: Router, private orderDetailsService: OrderDetailsService) {}

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


  redirectTo(uri:string){
     this.router.navigateByUrl('/products', {skipLocationChange: true}).then(()=>
     this.router.navigate([uri]));
  }

  //order
  id: number;

  completeTheOrder() {
    this.order.customer = this.countryForm.controls['countryControl'].value;
    this.orderService.createOrder(this.order)
      .subscribe(data =>
      console.log(data),
      error => console.log(error));
    // this.order = new Order();
    this.redirectTo('/products');
  }

  //maybe a wrong way
  order2: Order;
  orderDetails2: OrderDetails;
  num = 1;

  createOrderDetails(){
      this.order2 = new Order();
      //
      // console.log(this.orderArray);
      // this.order2 = this.orderArray[this.orderArray.length-1];
      // console.log(this.cartsArray.length-1);

      if (this.orderArray.length===0)
      {
          this.order2.id = this.num;
          this.order2.customer = this.countryForm.controls['countryControl'].value;
          console.log(this.orderArray.length);
      }
      else
      {
          this.order2.id = this.orderArray.length+1;
          this.order2.customer = this.countryForm.controls['countryControl'].value;
          // this.order2 = this.orderArray[this.orderArray.length-1];
          // console.log(this.orderArray.length-1);
      }

      for (let i = 0; i < this.cartsArray.length; i++) {
        // orderDetails: OrderDetails;
        this.orderDetails2 = new OrderDetails();
        this.orderDetails2.order = this.order2;
        this.orderDetails2.product = this.cartsArray[i].product;
        this.orderDetails2.quantity = this.cartsArray[i].quantity;
        this.orderDetails2.subtotal = this.cartsArray[i].subtotal;
        this.orderDetailsService.createOrderDetails(this.orderDetails2)
          .subscribe(data => console.log(data), error => console.log(error));
        console.log(this.orderDetails2);
      };
      this.redirectTo('/products');
  }

  callall(){
         this.completeTheOrder();
         this.redirectTo('/products');
         this.createOrderDetails();
         this.removeAll();
  }

  removeAll(){
    this.cartService.removeAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

}
