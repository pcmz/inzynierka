import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Cart } from "./cart";
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private baseUrl = 'http://localhost:8081/api/v1/carts';
  private url = `${this.baseUrl}/removeAll`;

  constructor(private http: HttpClient) {
  }

  useBroker(brokers:any){
   console.log(brokers);
  }

  getCarts(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createCarts(cart: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, cart);
  }

  updateCarts(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteCarts(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getCartsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  removeAll(): Observable<any> {
    return this.http.delete(`${this.url}`, { responseType: 'text' });
  }
}
