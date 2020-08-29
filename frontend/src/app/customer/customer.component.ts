import { Observable } from "rxjs";
import { CustomerService } from "./../customer.service";
import { Customer } from "./../customer";
import { Address } from "./../address";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { reduce } from 'rxjs/operators';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from '../product.service';
import { AddressService } from '../address.service';

@Component({
  selector: "app-cart",
  templateUrl: "./customer.component.html",
  styleUrls: ["./customer.component.css"]
})
export class CustomerComponent implements OnInit {

  customer: Customer;
  krs_number: number;
  address: Address;

  constructor(public activeModal: NgbActiveModal, private productService: ProductService, private customerService: CustomerService, private router: Router, private addressService: AddressService) {}

  ngOnInit() {
    this.customer = new Customer();
    this.address = new Address();
  }

  onSubmit() {
    this.customerService.getCustomerFromAPI(this.krs_number)
      .subscribe(data => {
        this.customer = data;
      }, error => console.log(error));
  }

  myFunc(){
    this.save();
  }

  save() {
    this.customerService.createCustomer(this.customer)
      .subscribe(data => console.log(data), error => console.log(error));
    this.customer = new Customer();
    this.gotoList();
  }

  gotoList() {
    this.router.navigate(['/products']);
  }

  redirectTo(uri: string) {
    this.router.navigateByUrl('/products', {skipLocationChange: true}).then(() =>
      this.router.navigate([uri]));
  }
}
