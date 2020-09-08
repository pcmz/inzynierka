import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {Delivery} from "../delivery";
import {DeliveryService} from "../delivery.service";

@Component({
  selector: "app-mock_delivery_website",
  templateUrl: "./mock_delivery_website.component.html",
  styleUrls: ["./mock_delivery_website.component.css"]
})

export class MockDeliveryWebsiteComponent implements OnInit {

  id: number;
  delivery: Delivery;

  constructor(private router: Router, private route: ActivatedRoute, private deliveryService: DeliveryService) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.deliveryService.getDeliveryByOrderId(this.id)
      .subscribe((delivery: Delivery) => {
        this.delivery = delivery
        console.log(delivery)
      })
  }
}
