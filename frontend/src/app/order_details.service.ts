import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { OrderDetails } from "./order_details";
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class OrderDetailsService {

  private baseUrl = 'http://localhost:8081/api/v1/order_details';

  constructor(private http: HttpClient) {
  }

  useBroker(brokers:any){
   console.log(brokers);
  }

  getOrderDetails(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createOrderDetails(orderDetails: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, orderDetails);
  }

  updateOrderDetails(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteOrderDetails(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getOrderDetailsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
