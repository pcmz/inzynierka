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

  createInvoice(order: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/invoice`, order);
  }

  getInvoice(orderId: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/invoice_by_order_id/${orderId}`)
  }

  getAllInvoices(): Observable<any> {
    return this.http.get(`${this.baseUrl}/invoice/all`)
  }

  getProformaInvoiceAddresByOrderId(order_id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/proforma_invoice_pdf_address/${order_id}`)
  }

  getVatInvoiceAddresByOrderId(order_id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/vat_invoice_pdf_address/${order_id}`)
  }

  promoteProformaInvoiceIntoVat(order_id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/promote_invoice/${order_id}`);
  }
}
