import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private baseUrl = 'http://localhost:8081/api/v1';

  constructor(private http: HttpClient) {
  }

  getInvoiceAddresByOrderId(order_id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/invoice_pdf_address/${order_id}`)
  }

  downloadFile(address: string): Observable<Blob> {
    return this.http.get<Blob>(`${address}`)
  }

  getInvoice(orderId: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/invoice_by_order_id/${orderId}`)
  }

  getAllInvoice(): Observable<any> {
    return this.http.get(`${this.baseUrl}/invoice/all`)
  }

  createInvoice(order: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/invoice`, order);
  }
}
