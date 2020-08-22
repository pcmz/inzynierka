import { Observable } from "rxjs";
import { OrderService } from "./../order.service";
import { OrderDetailsService } from "./../order_details.service";
import { OrderDetails } from "./../order_details";
import { Order } from "./../order";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { reduce, filter } from 'rxjs/operators';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: "app-order_details",
  templateUrl: "./order_details.component.html",
  styleUrls: ["./order_details.component.css"]
})
export class OrderDetailsComponent implements OnInit {

  id: number;
  order: Order;

  total = 0;

  // orderDetails: Observable<OrderDetails[]>;
  orderDetailsArray = [];

  constructor(private orderService: OrderService,
    private router: Router, private route: ActivatedRoute, private orderDetailsService: OrderDetailsService) {}

  ngOnInit() {
    this.reloadData();
    this.order = new Order();

    this.id = this.route.snapshot.params['id'];

    this.orderService.getOrder(this.id)
      .subscribe(data => {
        console.log(data)
        this.order = data;
      }, error => console.log(error));

      this.orderDetailsService.getOrderDetailsList()
      .subscribe(data => {
          this.orderDetailsArray = data;
      })
  }

  reloadData() {
    // this.orderDetails = this.orderDetailsService.getOrderDetailsList();
  }

  getItems() {
    return this.orderDetailsArray.filter((orderDetailsArray) => orderDetailsArray.order.id === this.order.id);
  }
}
