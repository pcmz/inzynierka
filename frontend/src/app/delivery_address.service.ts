import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeliveryAddressService {

  private baseUrl = 'http://localhost:8081/api/v1/delivery_addresses';

  constructor(private http: HttpClient) {
  }

  useBroker(brokers: any) {
    console.log(brokers);
  }

  getDeliveryAddress(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createDeliveryAddress(delivery_address: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, delivery_address);
  }

  deleteDeliveryAddress(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }

  getDeliveryAddressList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

}
