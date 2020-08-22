// import { EmployeeDetailsComponent } from './../employee-details/employee-details.component';
import { Observable } from "rxjs";
import { CustomerService } from "./../customer.service";
import { Customer } from "./../customer";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { reduce } from 'rxjs/operators';

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.css"]
})
export class CustomerComponent implements OnInit {
}
