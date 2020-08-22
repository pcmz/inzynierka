import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  private baseUrl = 'http://localhost:8081/api/v1/suppliers';

  constructor(private http: HttpClient) { }

  getSupplier(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getSuppliersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
