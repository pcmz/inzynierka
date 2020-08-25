import { Observable } from "rxjs";
import { OrderService } from "./../order.service";
import { OrderDetailsService } from "./../order_details.service";
import { Order } from "./../order";
import { Customer } from "./../customer";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { reduce } from 'rxjs/operators';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { OrderDetails } from "./../order_details";
import { CartService } from "./../cart.service";

@Component({
  selector: "app-order",
  templateUrl: "./order.component.html",
  styleUrls: ["./order.component.css"]
})
export class OrderComponent implements OnInit {
  orders: Observable<Order[]>;
  cartsArray = [];

  constructor(private orderService: OrderService,
    private router: Router, private orderDetailsService: OrderDetailsService, private cartService: CartService) {}

  ngOnInit() {
    this.reloadData();

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

  // orderDetails(id: number){
  //   this.router.navigate(['details', id]);
  // }
  //
  // updateOrder(id: number){
  //   this.router.navigate(['update', id]);
  // }

  order: Order;
  orderDetails: OrderDetails;

  showDetails(id: number){
  // this.order = new Order();
  // this.orderService.getOrder(id)
  //   .subscribe(data => {
  //     console.log(data)
  //     this.order = data;
  //     for (let i = 0; i < this.cartsArray.length; i++) {
  //       // orderDetails: OrderDetails;
  //       this.orderDetails = new OrderDetails();
  //       this.orderDetails.order = this.order;
  //       this.orderDetails.product = this.cartsArray[i].product;
  //       this.orderDetails.quantity = this.cartsArray[i].quantity;
  //       this.orderDetails.subtotal = this.cartsArray[i].subtotal;
  //       this.orderDetailsService.createOrderDetails(this.orderDetails)
  //         .subscribe(data => console.log(data), error => console.log(error));
  //       console.log(this.orderDetails);
  //     };
  //   }, error => console.log(error));

    this.router.navigate(['order_details', id]);
  }

  redirectTo(uri:string){
     this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
     this.router.navigate([uri]));
  }
}
