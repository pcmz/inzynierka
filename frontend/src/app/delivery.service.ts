import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {

  private baseUrl = 'http://localhost:8081/api/v1/delivery';

  constructor(private http: HttpClient) {
  }

  useBroker(brokers: any) {
    console.log(brokers);
  }

  getDelivery(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getDeliveryByOrderId(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}_by_order_id/${id}`);
  }

  createDelivery(delivery: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, delivery);
  }

  deleteDelivery(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }

  getDeliveryList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

}
